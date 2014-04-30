package untitled2;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionEvent;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.data.Item;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.server.*;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;

import java.util.Collection;

@Theme("mytheme")
@SuppressWarnings("serial")
public class MyVaadinUI extends UI
{
    private SpringContextHelper contextHelper = new SpringContextHelper();

    private TextField txtName1;
    private TextField txtSurname1;
    private TextField txtId2;
    private TextField txtName2;
    private TextField txtSurname2;
    private Table tblUsers;
    private Button btnAddUser;
    private Button btnAddUser12;
    private Button btnAddUser21;
    private Button btnSelectAll;

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = MyVaadinUI.class, widgetset = "untitled2.AppWidgetSet")
    public static class Servlet extends VaadinServlet
            implements SessionInitListener, SessionDestroyListener, HttpSessionActivationListener {
        @Override
        public void sessionDestroy(SessionDestroyEvent sessionDestroyEvent) {
            System.out.println(String.format("SESSION DESTROYED %s", VaadinSession.getCurrent().toString()));
        }

        @Override
        public void sessionInit(SessionInitEvent sessionInitEvent) throws ServiceException {
            System.out.println(String.format("SESSION INITIALIZED %s", VaadinSession.getCurrent().toString()));
        }

        @Override
        public void sessionWillPassivate(HttpSessionEvent httpSessionEvent) {
            System.out.println(String.format("SESSION PASSIVATED %s", VaadinSession.getCurrent().toString()));
        }

        @Override
        public void sessionDidActivate(HttpSessionEvent httpSessionEvent) {
            System.out.println(String.format("SESSION ACTIVATED %s", VaadinSession.getCurrent().toString()));
        }
    }

    @Override
    protected void init(VaadinRequest request) {
        this.addDetachListener(new DetachListener() {
            @Override
            public void detach(DetachEvent detachEvent) {
                System.out.println(String.format("UI DETACHED %s", detachEvent.getSource().toString()));
            }
        });

        this.addAttachListener(new AttachListener() {
            @Override
            public void attach(AttachEvent attachEvent) {
                System.out.println(String.format("UI ATTACHED %s", attachEvent.getSource().toString()));
            }
        });

        //SpringContextHelper helper = new SpringContextHelper();
        //helper.autowire(this);

        txtName1 = new TextField("Name 1:");
        txtSurname1 = new TextField("Surname 1:");

        txtId2 = new TextField("Id 2:");
        txtName2 = new TextField("Name 2:");
        txtSurname2 = new TextField("Surname 2:");
        
        tblUsers = new Table("Users");
        tblUsers.addContainerProperty("name", String.class, null);
        tblUsers.addContainerProperty("surname", String.class, null);

        btnAddUser = new Button("Add User");
        btnAddUser12 = new Button("Add User 1 2");
        btnAddUser21 = new Button("Add User 2 1");
        btnSelectAll = new Button("Select All");

        btnAddUser.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                User user1 = new User();
                user1.setName(txtName1.getValue());
                user1.setSurname(txtSurname1.getValue());

                try {
                    BusinessLogic bl = (BusinessLogic) contextHelper.getBean("businessLogic");
                    bl.createUser(user1);
                    Notification.show("Info", "User added", Notification.Type.HUMANIZED_MESSAGE);
                } catch (Exception e) {
                    Notification.show("Error", e.getMessage(), Notification.Type.ERROR_MESSAGE);
                }
            }
        });

        btnAddUser12.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                User user1 = new User();
                user1.setName(txtName1.getValue());
                user1.setSurname(txtSurname1.getValue());

                User user2 = new User();
                user2.setId(Integer.parseInt(txtId2.getValue()));
                user2.setName(txtName2.getValue());
                user2.setSurname(txtSurname2.getValue());

                try {
                    BusinessLogic bl = (BusinessLogic) contextHelper.getBean("businessLogic");
                    bl.createTwoUsers12(user1, user2);
                    Notification.show("Info", "User added", Notification.Type.HUMANIZED_MESSAGE);
                } catch (Exception e) {
                    Notification.show("Error", e.getMessage(), Notification.Type.ERROR_MESSAGE);
                }
            }
        });

        btnAddUser21.addClickListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                User user1 = new User();
                user1.setName(txtName1.getValue());
                user1.setSurname(txtSurname1.getValue());

                User user2 = new User();
                user2.setId(Integer.parseInt(txtId2.getValue()));
                user2.setName(txtName2.getValue());
                user2.setSurname(txtSurname2.getValue());

                try {
                    BusinessLogic bl = (BusinessLogic) contextHelper.getBean("businessLogic");
                    bl.createTwoUsers21(user1, user2);
                    Notification.show("Info", "User added", Notification.Type.HUMANIZED_MESSAGE);
                } catch (Exception e) {
                    Notification.show("Error", e.getMessage(), Notification.Type.ERROR_MESSAGE);
                }
            }
        });

        btnSelectAll.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent clickEvent) {
                BusinessLogic bl = (BusinessLogic) contextHelper.getBean("businessLogic");
                Collection<User> users = bl.getAllUsers();
                tblUsers.removeAllItems();
                int i = 0;
                for (User user : users) {
                    tblUsers.addItem(new Object[]{
                            new String(user.getName()),
                            new String(user.getSurname())
                    }, i * 1000);
                    i++;
                }

                int count = bl.getUsersCount();
                Notification.show("Users count: " + count);
            }
        });

        tblUsers.addItemClickListener(new ItemClickEvent.ItemClickListener() {
            @Override
            public void itemClick(ItemClickEvent itemClickEvent) {
                Integer id = (Integer) itemClickEvent.getItemId();
                Item item = tblUsers.getItem(id);
                String s = String.format("%s %s %s %s",
                        id,
                        item.getItemProperty("id").getValue(),
                        item.getItemProperty("name").getValue(),
                        item.getItemProperty("surname").getValue()
                );
                Notification.show("Info", s, Notification.Type.HUMANIZED_MESSAGE);
            }
        });

        Button logout = new Button("Logout");
        logout.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(ClickEvent event) {
                // Close the VaadinSession
                getSession().close();
                VaadinSession.getCurrent().getSession().invalidate();

                // Redirect from the page
                getUI().getPage().setLocation(
                        "/myapp/logoutpage.html");
            }
        });

        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        setContent(layout);


        // Configure the error handler for the UI
        UI.getCurrent().setErrorHandler(new DefaultErrorHandler() {
            @Override
            public void error(com.vaadin.server.ErrorEvent event) {
                // Find the final cause
                String cause = "<b>The click failed because:</b><br/>";
                for (Throwable t = event.getThrowable(); t != null;
                     t = t.getCause())
                    if (t.getCause() == null) // We're at final cause
                        cause += t.getClass().getName() + "<br/>";

                // Display the error message in a custom fashion
                layout.addComponent(new Label(cause, ContentMode.HTML));

                // Do the default error handling (optional)
                //doDefault(event);
            }
        });

        VerticalLayout layout1 = new VerticalLayout();
        layout1.setMargin(true);
        layout1.addComponent(txtName1);
        layout1.addComponent(txtSurname1);

        VerticalLayout layout2 = new VerticalLayout();
        layout2.setMargin(true);
        layout2.addComponent(txtId2);
        layout2.addComponent(txtName2);
        layout2.addComponent(txtSurname2);

        VerticalLayout layout3 = new VerticalLayout();
        layout3.setMargin(true);
        layout3.addComponent(btnAddUser);
        layout3.addComponent(btnAddUser12);
        layout3.addComponent(btnAddUser21);
        layout3.addComponent(btnSelectAll);

        HorizontalLayout hLayout1 = new HorizontalLayout();
        hLayout1.setMargin(true);
        hLayout1.addComponent(layout1);
        hLayout1.addComponent(layout2);
        hLayout1.addComponent(layout3);

        HorizontalLayout hLayout2 = new HorizontalLayout();
        hLayout2.setMargin(true);
        hLayout2.addComponent(logout);

        layout.addComponent(hLayout2);
        layout.addComponent(hLayout1);
        layout.addComponent(tblUsers);
    }

}
