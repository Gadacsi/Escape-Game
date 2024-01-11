import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class ItemManager {
    public ItemTile[] itemTiles;

    public ItemManager() {
        itemTiles = new ItemTile[20];
        getItemTileImage();
    }

    public void getItemTileImage() {

        itemTiles[0] = new ItemTile();
        itemTiles[0].image = new ImageIcon(Objects.requireNonNull(getClass().getResource("items/backpack.png")));
        itemTiles[0].name = "backpack";
        itemTiles[0].mapX = 64;
        itemTiles[0].mapY = 64;
        itemTiles[1] = new ItemTile();
        itemTiles[1].image = new ImageIcon(Objects.requireNonNull(getClass().getResource("items/key.png")));
        itemTiles[1].name = "key";
        itemTiles[1].mapX = 32;
        itemTiles[1].mapY = 64;
        itemTiles[2] = new ItemTile();
        itemTiles[2].image = new ImageIcon(Objects.requireNonNull(getClass().getResource("items/sword.png")));
        itemTiles[2].name = "sword";
        itemTiles[2].mapX = 240;
        itemTiles[2].mapY = 352;
        itemTiles[3] = new ItemTile();
        itemTiles[3].image = new ImageIcon(Objects.requireNonNull(getClass().getResource("items/door_closed.png")));
        itemTiles[3].name = "door closed";
        itemTiles[3].mapX = 688;
        itemTiles[3].mapY = 272;
        itemTiles[4] = new ItemTile();
        itemTiles[4].image = new ImageIcon(Objects.requireNonNull(getClass().getResource("items/door_open.png")));
        itemTiles[4].name = "door open";
        itemTiles[4].mapX = 688;
        itemTiles[4].mapY = 272;
        itemTiles[4].show = false;
    }

    public void drawItem(Graphics2D graphics2D) {
        if (itemTiles[0].show)
            graphics2D.drawImage(itemTiles[0].image.getImage(), itemTiles[0].mapX, itemTiles[0].mapY, 16, 16, null);
        if (itemTiles[1].show)
            graphics2D.drawImage(itemTiles[1].image.getImage(), itemTiles[1].mapX, itemTiles[1].mapY, 16,16,null);
        if (itemTiles[2].show)
            graphics2D.drawImage(itemTiles[2].image.getImage(), itemTiles[2].mapX, itemTiles[2].mapY, 16, 16,null);
        if (itemTiles[3].show)
            graphics2D.drawImage(itemTiles[3].image.getImage(), itemTiles[3].mapX, itemTiles[3].mapY, 16, 16, null);
        if (itemTiles[4].show)
            graphics2D.drawImage(itemTiles[4].image.getImage(), itemTiles[4].mapX, itemTiles[4].mapY, 16, 16, null);
    }
}
