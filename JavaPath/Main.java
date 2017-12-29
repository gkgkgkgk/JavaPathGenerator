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

	int minimumStep = 0;
	
	int width = 100;
	
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
        generateNewRow();
		minimumStep++;
				
		
		if(minimumStep >= 20){
			if(Math.random() < 0.5){
				rightD = true;
				minimumStep = 0;
				width = (int)(Math.random() * 101);
			}
			if(Math.random() >= 0.5){
				rightD = false;
				minimumStep = 0;
			}
		}
			
	if(left.getX() < 100){
			rightD = true;
			minimumStep = 0;

		}
		if(left.getX() > 200){
			rightD = false;
			minimumStep = 0;

		}			
				
		if(!rightD){
			left.setX(left.getX() -1);
			right.setX(left.getX() + width);
		}
		else if(rightD){
			left.setX(left.getX() +1);
			right.setX(left.getX() + width);
		}
		
		
		/*left.setX((int) (175.0 * Math.cos(minimumStep)));
		right.setX(left.getX() + 100);*/
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