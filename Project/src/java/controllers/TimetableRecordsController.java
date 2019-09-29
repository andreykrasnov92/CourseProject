package controllers;

import controllers.util.JsfUtil;
import controllers.util.PaginationHelper;
import entities.TimetableRecords;
import facades.TimetableRecordsFacade;
import java.io.Serializable;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

@ManagedBean(name = "timetableRecordsController")
@SessionScoped
public class TimetableRecordsController implements Serializable {

    private TimetableRecords current;
    private DataModel items = null;
    @EJB
    private TimetableRecordsFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public TimetableRecordsController() {
    }

    public TimetableRecords getSelected() {
        if (current == null) {
            current = new TimetableRecords();
            current.setTimetableRecordsPK(new entities.TimetableRecordsPK());
            selectedItemIndex = -1;
        }
        return current;
    }

    private TimetableRecordsFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {
                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (TimetableRecords) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new TimetableRecords();
        current.setTimetableRecordsPK(new entities.TimetableRecordsPK());
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage("Запись была успешно добавлена в расписание");
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (TimetableRecords) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage("Запись в расписании была успешно отредактирована");
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (TimetableRecords) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage("Запись была успешно удалена из расписания");
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public String userNext() {
        getPagination().nextPage();
        recreateModel();
        return "UserList";
    }

    public String userPrevious() {
        getPagination().previousPage();
        recreateModel();
        return "UserList";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    @FacesConverter(forClass = TimetableRecords.class)
    public static class TimetableRecordsControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TimetableRecordsController controller = (TimetableRecordsController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "timetableRecordsController");
            return controller.ejbFacade.find(getKey(value));
        }

        entities.TimetableRecordsPK getKey(String value) {
            entities.TimetableRecordsPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new entities.TimetableRecordsPK();
            key.setGroupId(Integer.parseInt(values[0]));
            key.setWeekParity(Integer.parseInt(values[1]));
            key.setWeekday(values[2]);
            key.setPairNumber(Integer.parseInt(values[3]));
            return key;
        }

        String getStringKey(entities.TimetableRecordsPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getGroupId());
            sb.append(SEPARATOR);
            sb.append(value.getWeekParity());
            sb.append(SEPARATOR);
            sb.append(value.getWeekday());
            sb.append(SEPARATOR);
            sb.append(value.getPairNumber());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof TimetableRecords) {
                TimetableRecords o = (TimetableRecords) object;
                return getStringKey(o.getTimetableRecordsPK());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + TimetableRecords.class.getName());
            }
        }
    }
}
