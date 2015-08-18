package hw10;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Example playing piece. This class is only here as an example,
 * and may be discarded.
 */
public class RoundPiece extends Piece {
    
    public Color color = Color.black;
    
//    public String toString() {
//        return ColorNames.toString(color) + " " + super.toString();
//    }
    
    /**
     * Constructs a <code>RoundPiece</code>.
     **/
    public RoundPiece() {
    }
    
    /**
     * Constructs a <code>RoundPiece</code> of the given color.
     * 
     * @param color The <code>Color</code> of the new piece.
     **/
     RoundPiece(Color color) {
        this.color = color;
    }
    
    /**
     * Draws this <code>RoundPiece</code> on the given <code>Graphics</code>.
     * Drawing should be limited to the provided <code>java.awt.Rectangle</code>.
     * 
     * @param g The graphics on which to draw.
     * @param r The rectangle in which to draw.
     */
    public void paint(Graphics g, Rectangle r) {
        Color oldColor = g.getColor();
        g.setColor(color); //Color of the piece
        g.fillOval(r.x, r.y, r.width, r.height); //Draw the oval of that particular color within that rectangle.
        if (this.equals(board.getSelectedPiece())) {
            g.setColor(Color.BLACK);
            g.drawOval(r.x, r.y, r.width, r.height);
        }
        g.setColor(oldColor);
    }
}
