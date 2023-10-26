package memorymanagementsimulator.frontend;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RamComponent {
    int pageQuantity;
    JPanel panel;
    private List<PageComponent> pageComponents;
    public RamComponent(JPanel panel){
        pageComponents = new ArrayList<>();
        this.pageQuantity = 100;
        this.panel = panel;
        initRam(this.panel);
    }

    private void initRam(JPanel panel){
        if (panel == null){
            throw new RuntimeException("JPanel is null");
        }
        int x = 0;
        int y = 0;
        int ramPageWidth = panel.getWidth() / pageQuantity;
        int ramPageHeight = 25;
        for (int i = 0; i < pageQuantity; i++){
            x = x + ramPageWidth;

            PageComponent pageComponent = new PageComponent(x, y, ramPageWidth, ramPageHeight);

            pageComponent.setColor(null);
            pageComponents.add(pageComponent);
            panel.add(pageComponent);
        }
    }

    public void setPageColor(int index, Color c){
        if (index >= pageComponents.size()){
            throw new RuntimeException("Index size is more than ram size");
        }
        pageComponents.get(index).setColor(c);
        this.panel.repaint();
    }
    public List<PageComponent> getPageComponents() {
        return pageComponents;
    }

    public void setPageComponents(List<PageComponent> pageComponents) {
        this.pageComponents = pageComponents;
    }
}
