package Controller;

import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.apache.lucene.queryparser.classic.ParseException;

import View.Pantalla;
import aplicacion.Searcher;

public class PantallaController implements ActionListener,ListSelectionListener{
	private Pantalla vista;
	private ArrayList<String> lista;	
	
	String index = "C:.\\index";
	
	String indexp1 = "C:.\\index";
	String indexp2 = "C:.\\index";
	String indexg1 = "C:.\\index";
	String indexg2 = "C:.\\index";
	
	
	
	
	
	public PantallaController(Pantalla _vista) {
		this.vista=_vista;
		this.vista.modelTable.addColumn("Titulos");
		_init_();
    }
    
    public void _init_(){
    	vista.btnSearch.addActionListener(this);
        vista.chckbxWikig1.addActionListener(this);
        vista.chckbxWikig2.addActionListener(this);
        vista.chckbxWikip1.addActionListener(this);
        vista.chckbxWikip2.addActionListener(this);
        
        vista.listSelectionModel = vista.table.getSelectionModel();
        vista.listSelectionModel.addListSelectionListener(this);
        
        
        vista.table.setSelectionModel(vista.listSelectionModel);
            
    }
	
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() ==vista.btnSearch){
        	String query = vista.textSearch.getText();
        	limpiarTabla();
        	llenarTabla(query);
        }else if (e.getSource() == vista.chckbxWikig1) {
        	 index = indexg1;
        	// vista.chckbxWikig1.setSelected(true);
             vista.chckbxWikig2.setSelected(false);
             vista.chckbxWikip1.setSelected(false);
             vista.chckbxWikip2.setSelected(false);
        }else if (e.getSource() == vista.chckbxWikig2) {
        	index = indexg2;
        	vista.chckbxWikig1.setSelected(false);
           // vista.chckbxWikig2.setSelected(true);
            vista.chckbxWikip1.setSelected(false);
            vista.chckbxWikip2.setSelected(false);
        }else if (e.getSource() == vista.chckbxWikip1) {
        	index = indexp1;
        	vista.chckbxWikig1.setSelected(false);
            vista.chckbxWikig2.setSelected(false);      
           // vista.chckbxWikip1.setSelected(false);
            vista.chckbxWikip2.setSelected(false);
        }else if (e.getSource() == vista.chckbxWikip2) {
        	index = indexp2;
        	vista.chckbxWikig1.setSelected(false);
            vista.chckbxWikig2.setSelected(false);
            vista.chckbxWikip1.setSelected(false);
           // vista.chckbxWikip2.setSelected(true);
        	
        }
    }
    
    @Override
	public void valueChanged(ListSelectionEvent e) {
		// TODO Auto-generated method stub
		//if (vista.table.getSelectedRow() > -1) {
            // print first column value from selected row
            System.out.println("Dato :"+vista.table.getValueAt(vista.table.getSelectedRow(), 0).toString());
        //}
	}
    
    public void limpiarTabla() {
    	int fila = this.vista.modelTable.getRowCount();
    	System.out.print("TAMANO TABLA"+fila);
    	for(int i = 0 ; i < fila; i++) {
    		this.vista.modelTable.removeRow(0);
    	}
    	System.out.print("TABLA LIMPIA");
    } 
    
    public String elegirTxt() {
    	
    	
    	
    	
    	
    	return "hola";
    }
    
    public void validarCheck() {
    	
    }
	
	public void llenarTabla(String query) {
		
		try {
			this.lista = Searcher.searchFile(query,index);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String []info = new String[1];
		
		for( int i = 0; i < this.lista.size(); i++ ) {
			info[0]=this.lista.get(i);
			this.vista.modelTable.addRow(info);
		}
		
		this.vista.table.setModel(this.vista.modelTable);
	}

	
	
	
	
}