import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main extends JPanel implements ActionListener {


    JFrame w = new JFrame();

    ArrayList < Cell > cells = new ArrayList < Cell > ();

    javax.swing.Timer t;

    CellRemover left = new CellRemover(150, 100);

    CellRemover right = new CellRemover(250, 100);

	boolean rightD = false;
	
    public Main() {
        t = new javax.swing.Timer(10, this);
        t.start();

        setLayout(null);
        w.setContentPane(this);
        w.setSize(800, 600);
        w.setVisible(true);
        w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        for (int y = 100; y < 300; y++) {
            for (int x = 100; x < 300; x++) {
                cells.add(new Cell(x, y));
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Cell cell: cells) {
            g.fillRect(cell.getX(), cell.getY(), 1, 1);
        }
    }


    public void actionPerformed(ActionEvent e) {
        //System.out.println("Run");
        generateNewRow();
		
		if(!rightD){
			left.setX(left.getX() -1);
			right.setX(left.getX() + 100);
		}
		else if(rightD){
			left.setX(left.getX() +1);
			right.setX(left.getX() + 100);
		}
		if(left.getX() < 100 && !rightD){
			rightD = true;
		}
		if(left.getX() > 200 && rightD){
			rightD = false;
		}
		
        repaint();
    }

    public void generateNewRow() {

        for (Iterator < Cell > iterator = cells.iterator(); iterator.hasNext();) {
            Cell cell = iterator.next();
            cell.setY(cell.getY() + 1);

            if (cell.getY() >= 300) {
                iterator.remove();
            }
        }
		for(int i = 100; i < 300; i++){
			if(i < left.getX() || i > right.getX()){
				cells.add(new Cell(i, 100));
			}
		}

    }


    public static void main(String[] args) {
        new Main();
    }

}