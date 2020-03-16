package UI;

import java.awt.*;

public abstract class UIElement {
    //---Variables
        int xPos, yPos, xSize, ySize;
        Rectangle bounds;
    //---Constructor
        public UIElement(int xPos, int yPos, int xSize, int ySize) {
            this.xPos = xPos;
            this.yPos = yPos;
            this.xSize = xSize;
            this.ySize = ySize;
            this.bounds = new Rectangle(xPos, yPos, xSize, ySize);
        }
    //---Methods
        public abstract void render(Graphics g);
        public abstract void update(double deltaTime);
        public abstract void onClicked();
        public void isClicked(Point clickLocation) {
            if(bounds.contains(clickLocation))  {
                onClicked();
            }
        }
        public int getxPos() {
            return xPos;
        }

        public void setxPos(int xPos) {
            this.xPos = xPos;
        }

        public int getyPos() {
            return yPos;
        }

        public void setyPos(int yPos) {
            this.yPos = yPos;
        }

        public int getxSize() {
            return xSize;
        }

        public void setxSize(int xSize) {
            this.xSize = xSize;
        }

        public int getySize() {
            return ySize;
        }

        public void setySize(int ySize) {
            this.ySize = ySize;
        }
}
