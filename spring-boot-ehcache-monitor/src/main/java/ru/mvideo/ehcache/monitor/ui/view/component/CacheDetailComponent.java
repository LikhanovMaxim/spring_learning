package ru.mvideo.ehcache.monitor.ui.view.component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vaadin.event.ShortcutAction;
import com.vaadin.event.ShortcutListener;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.CustomComponent;
import com.vaadin.ui.Grid;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.renderers.ComponentRenderer;
import com.vaadin.ui.themes.ValoTheme;
import lombok.EqualsAndHashCode;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.springframework.util.StringUtils;
import ru.mvideo.ehcache.monitor.utils.DateTimeUtils;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
public class CacheDetailComponent extends CustomComponent implements View {

    private static final long serialVersionUID = -7153560947634231247L;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final String SEARCH_DEFAULT = "";

    private final transient CacheManager cacheManager;
    private final ObjectMapper objectMapper;
    private transient Cache ehcache;
    private Grid<Cache> infoGrid;
    private Grid<Element> detailGrid;
    private TextField searchTextField;

    public CacheDetailComponent(CacheManager cacheManager, ObjectMapper objectMapper) {
        this.cacheManager = cacheManager;
        this.objectMapper = objectMapper;
    }

    private void init(String name) {
        this.ehcache = this.cacheManager.getCache(name);
        this.infoGrid = createCacheInfoGrid();
        this.detailGrid = createDetailGrid();
        this.searchTextField = createSearchTextField();
        VerticalLayout content = new VerticalLayout();
        content.addComponent(createTitleBar());
        content.addComponent(createControlBar());
        content.addComponent(this.infoGrid);
        content.addComponent(this.detailGrid);
        setSizeFull();
        setCompositionRoot(content);
    }

    private TextField createSearchTextField() {
        TextField textField = new TextField();
        textField.addShortcutListener(new ShortcutListener("Enter Keyword",
                ShortcutAction.KeyCode.ENTER, new int[]{}) {
            @Override
            public void handleAction(Object sender, Object target) {
                searchAction();
            }
        });
        return textField;
    }

    private HorizontalLayout createTitleBar() {
        HorizontalLayout titleBar = new HorizontalLayout();
        Label title = new Label("EHCACHE DETAIL");
        titleBar.addComponent(title);
        titleBar.setExpandRatio(title, 1.0f); // Expand
        title.addStyleNames(ValoTheme.LABEL_H1, ValoTheme.LABEL_BOLD, ValoTheme.LABEL_COLORED);
        return titleBar;
    }

    private HorizontalLayout createControlBar() {
        HorizontalLayout controlBar = new HorizontalLayout();
        controlBar.addComponent(new Button("Refresh", (Button.ClickListener) event -> refresh()));
        controlBar.addComponent(new Button("Flush", (Button.ClickListener) event -> {
            this.ehcache.removeAll();
            refresh();
        }));
        controlBar.addComponent(createSearchBox());
        controlBar.setWidth("50%");
        return controlBar;
    }

    private HorizontalLayout createSearchBox() {
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        this.searchTextField.setWidth("70%");
        horizontalLayout.addComponent(this.searchTextField);
        Button button = new Button(VaadinIcons.SEARCH);
        button.addClickListener(event -> searchAction());
        button.setWidth("30%");
        horizontalLayout.addComponent(button);
        return horizontalLayout;
    }

    private Grid<Cache> createCacheInfoGrid() {
        Grid<Cache> grid = new Grid<>();
        grid.addColumn(Cache::getName).setCaption("Name");
        grid.addColumn(cache -> (
                (Double)
                        (((double) cache.getStatistics().cacheHitCount()) / ((double) (cache.getStatistics().cacheMissCount() + cache.getStatistics().cacheHitCount())) * 100))
                .intValue() + "%")
                .setCaption("Hit Ratio");
        grid.addColumn(cache -> cache.getCacheConfiguration().getMaxEntriesLocalHeap()).setCaption("Max Size");
        grid.addColumn(Cache::getSize).setCaption("Size");
        grid.addColumn(Cache::getStatus).setCaption("Status");
        grid.addColumn(cache -> cache.getCacheConfiguration().getTimeToIdleSeconds()).setCaption("TTldle(s)");
        grid.addColumn(cache -> cache.getCacheConfiguration().getTimeToLiveSeconds()).setCaption("TTLive(s)");
        grid.addColumn(cache -> cache.getStatistics().cacheHitCount()).setCaption("hit");
        grid.addColumn(cache -> cache.getStatistics().cacheMissExpiredCount()).setCaption("miss : Expire");
        grid.addColumn(cache -> cache.getStatistics().cacheMissNotFoundCount()).setCaption("miss : Not Found");
        grid.setItems(Collections.singletonList(this.ehcache));
        grid.setWidth("100%");
        grid.setSelectionMode(Grid.SelectionMode.NONE);
        grid.setHeightByRows(1);
        return grid;
    }

    private Grid<Element> createDetailGrid() {
        Grid<Element> grid = new Grid<>();
        grid.addColumn(Element::getObjectKey).setMaximumWidth(400).setCaption("Name");
        grid.addColumn(element -> {
            Button button = new Button(VaadinIcons.PLUS_CIRCLE);
            button.addClickListener(event -> {
                ValueWindow valueWindow = new ValueWindow(writeAsString(element));
                UI.getCurrent().addWindow(valueWindow);
            });
            return button;
        }, new ComponentRenderer()).setCaption("Value");
        grid.addColumn(element -> DateTimeUtils.ofPattern(element.getCreationTime(), FORMATTER)).setCaption("Create Time");
        grid.addColumn(element -> DateTimeUtils.ofPattern(element.getLastAccessTime(), FORMATTER)).setCaption("Access Time");
        grid.addColumn(element -> DateTimeUtils.ofPattern(element.getLastUpdateTime(), FORMATTER)).setCaption("Update Time");
        grid.addColumn(Element::getVersion).setCaption("Version");
        grid.addColumn(Element::getHitCount).setCaption("Hit");
        grid.addColumn(element -> {
            Button button = new Button(VaadinIcons.TRASH);
            button.addClickListener(event -> {
                this.ehcache.remove(element.getObjectKey());
                refresh();
            });
            return button;
        }, new ComponentRenderer()).setCaption("Remove cache");
        grid.setItems(this.ehcache.getAll(getKeys(this.ehcache, SEARCH_DEFAULT)).values());
        grid.setWidth("100%");
        grid.setSelectionMode(Grid.SelectionMode.NONE);
        int ehcacheSize = this.ehcache.getKeys().size();
        if (ehcacheSize != 0) {
            grid.setHeightByRows(ehcacheSize > 15 ? 15 : ehcacheSize);
        }
        return grid;
    }

    private String writeAsString(Element element) {
        try {
            return objectMapper.writeValueAsString(element.getObjectValue());
        } catch (JsonProcessingException e) {
            return "[can't show - " + e.getMessage() + "]";
        }
    }

    @SuppressWarnings("unchecked")
    private List<?> getKeys(Cache ehcache, String keyword) {
        return (List<?>) ehcache.getKeys();
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        String cacheName = event.getParameterMap().getOrDefault(Menu.CACHE_PARAMETER_KEY, "");
        if ("".equals(cacheName)) {
            event.getNavigator().navigateTo(cacheName);
            return;
        }
        init(cacheName);
    }

    private void refresh() {
        refreshTextField();
        refreshInfoGrid();
        refreshDetailGrid();
    }

    private void refreshTextField() {
        this.searchTextField.setValue(SEARCH_DEFAULT);
    }

    private void refreshInfoGrid() {
        this.infoGrid.setItems(Collections.singletonList(this.ehcache));
    }

    private void refreshDetailGrid() {
        this.detailGrid.setItems(this.ehcache.getAll(getKeys(this.ehcache, SEARCH_DEFAULT)).values());
    }

    private void searchAction() {
        String value = this.searchTextField.getValue();
        if (StringUtils.isEmpty(value)) {
            value = "";
        }
        this.detailGrid.setItems(ehcache.getAll(getKeys(ehcache, value)).values());
    }

    private class ValueWindow extends Window {
        private static final long serialVersionUID = 8147410120871496698L;

        ValueWindow(String value) {
            super("Value"); // Set window caption
            center();
            TextArea textArea = new TextArea("", value);
            textArea.setWidth(400, Unit.PIXELS);
            textArea.setRows(10);

            setContent(textArea);
        }
    }
}
