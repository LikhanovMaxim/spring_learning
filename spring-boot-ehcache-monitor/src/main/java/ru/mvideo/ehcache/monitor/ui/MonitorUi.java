package ru.mvideo.ehcache.monitor.ui;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.spring.navigator.SpringNavigator;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.themes.ValoTheme;
import lombok.EqualsAndHashCode;
import net.sf.ehcache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import ru.mvideo.ehcache.monitor.ui.view.component.Menu;

@Theme(value = "valo")
@Title(value = "Ehcache Monitoring")
@SpringUI(path = "admin/ehcache")
@EqualsAndHashCode(callSuper = false)
public class MonitorUi extends UI implements ViewChangeListener {

    private static final long serialVersionUID = -2427603230914432279L;
    private final transient CacheManager cacheManager;

    private final SpringNavigator springNavigator;

    private Menu menu;

    private CssLayout container;

    @Autowired
    public MonitorUi(org.springframework.cache.CacheManager cacheManager, SpringNavigator springNavigator) {
        this.cacheManager = ((EhCacheCacheManager) cacheManager).getCacheManager();
        this.springNavigator = springNavigator;
    }

    /**
     * start point for render pages
     *
     * @param request
     */
    @Override
    protected void init(VaadinRequest request) {
        initStyle();
        initMenu();
        initContainer();
        initNavigator();
        setContent(createLayout());
        setSizeFull();
        addStyleName("v-scrollable");
    }

    private void initStyle() {
        addStyleName(ValoTheme.UI_WITH_MENU);
    }

    private void initNavigator() {
        this.springNavigator.init(this, this.container);
        this.springNavigator.addViewChangeListener(this);
        this.springNavigator.navigateTo("");
    }

    private HorizontalLayout createLayout() {
        HorizontalLayout layout = new HorizontalLayout();
        layout.setStyleName("main-screen");
        layout.addComponent(this.menu);
        layout.addComponent(this.container);
        layout.setExpandRatio(this.container, 1);
        layout.setSizeFull();
        layout.setSpacing(true);
        layout.setHeight(3000, Unit.PIXELS);
        return layout;
    }

    private void initMenu() {
        this.menu = new Menu(this.springNavigator, this.cacheManager);
    }

    private void initContainer() {
        CssLayout viewContainer = new CssLayout();
        viewContainer.addStyleName("valo-content");
        viewContainer.setSizeFull();
        this.container = viewContainer;
    }

    @Override
    public boolean beforeViewChange(ViewChangeEvent event) {
        return true;
    }

    @Override
    public void afterViewChange(ViewChangeEvent event) {
        this.menu.setActiveView(event.getViewName());
    }
}
