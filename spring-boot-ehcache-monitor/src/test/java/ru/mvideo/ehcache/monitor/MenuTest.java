package ru.mvideo.ehcache.monitor;

import com.vaadin.navigator.Navigator;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CssLayout;
import net.sf.ehcache.CacheManager;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.mvideo.ehcache.monitor.ui.view.component.Menu;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import static ru.mvideo.ehcache.monitor.ui.view.component.Menu.ALL_CACHES;

public class MenuTest {

    @Mock
    private Navigator navigator;

    @Mock
    private CacheManager cacheManager;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test1() {
        String cache1 = "Cache1";
        String cache2 = "Cache2";
        when(this.cacheManager.getCacheNames()).thenReturn(new String[]{cache1});
        case1(cache1);
        when(this.cacheManager.getCacheNames()).thenReturn(new String[]{cache1, cache2});
        case2(cache1, cache2);

    }

    private void case1(String cache1) {
        Menu menu = new Menu(this.navigator, this.cacheManager);

        Component component = menu.getComponent(0);
        assertTrue(component instanceof CssLayout);
        CssLayout menuPart = (CssLayout) component;

        Component component2 = menuPart.getComponent(2);
        assertTrue(component2 instanceof CssLayout);
        CssLayout menuItemsLayout = (CssLayout) component2;

        assertEquals(2, menuItemsLayout.getComponentCount());

        Component component3 = menuItemsLayout.getComponent(0);
        assertTrue(component3 instanceof Button);
        Button allButton = (Button) component3;
        assertEquals(ALL_CACHES, allButton.getCaption());

        Component component4 = menuItemsLayout.getComponent(1);
        assertTrue(component4 instanceof Button);
        Button cacheButton1 = (Button) component4;
        assertEquals(cache1, cacheButton1.getCaption());
    }

    private void case2(String cache1, String cache2) {
        Menu menu = new Menu(this.navigator, this.cacheManager);

        Component component = menu.getComponent(0);
        assertTrue(component instanceof CssLayout);
        CssLayout menuPart = (CssLayout) component;

        Component component2 = menuPart.getComponent(2);
        assertTrue(component2 instanceof CssLayout);
        CssLayout menuItemsLayout = (CssLayout) component2;

        assertEquals(3, menuItemsLayout.getComponentCount());

        Component component3 = menuItemsLayout.getComponent(0);
        assertTrue(component3 instanceof Button);
        Button allButton = (Button) component3;
        assertEquals(ALL_CACHES, allButton.getCaption());

        Component component4 = menuItemsLayout.getComponent(1);
        assertTrue(component4 instanceof Button);
        Button cacheButton1 = (Button) component4;
        assertEquals(cache1, cacheButton1.getCaption());

        Component component5 = menuItemsLayout.getComponent(2);
        assertTrue(component5 instanceof Button);
        Button cacheButton2 = (Button) component5;
        assertEquals(cache2, cacheButton2.getCaption());
    }
}
