
package principal;

import service.MenuService;


public class Principal {
    public static void main(String[] args) throws Exception {
        
        MenuService menu = new MenuService();
        menu.menuLibreria();
    }
}
