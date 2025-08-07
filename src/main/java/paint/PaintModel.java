package paint;

import java.util.ArrayList;
import java.util.Observable;
import javafx.scene.paint.Color;

public class PaintModel extends Observable {

        private ArrayList<Drawable> drawables = new ArrayList<>();
        private ArrayList<Drawable> tempDrawables = new ArrayList<>();
        private Color color;

        public void addDrawable(Drawable d) {
                this.drawables.add(d);
                notifyChange();
        }

        public ArrayList<Drawable> getDrawables() {
                return drawables;
        }

        public void addTempDrawable(Drawable d) {
                this.tempDrawables.add(d);
                notifyChange();
        }

        public ArrayList<Drawable> getTempDrawables() {
                return tempDrawables;
        }

        public void clearTempDrawables() {
                tempDrawables.clear();
                notifyChange();
        }

        public void clearDrawables() {
                drawables.clear();
                notifyChange();
        }

        protected void notifyChange() {
                setChanged();
                notifyObservers();
        }

        public void Undo() {
                this.drawables.removeLast();
                notifyChange();
        }
}
