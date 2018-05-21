package Classes;

import java.io.File;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;

import java.awt.*;
import java.awt.event.*;

import java.util.Iterator;
import java.util.List;

import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.model.Resource;
import org.eclipse.rdf4j.model.util.Models;

import com.hp.hpl.jena.rdf.model.*;
import com.hp.hpl.jena.rdf.model.Literal;
//import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.util.FileManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;
import javax.xml.bind.JAXBException;

public class app extends JFrame {
	
	private static String filePath = "C:\\Users\\7\\workspace\\ZTI_Project\\mobile-phone-ontology\\phonesListInXML.xml";
	
	private static List<Model> modelList;
	
	public app() {
		modelList = MobilePhone.GeneratePhoneModels(XMLHelper.ReadAllPhones(filePath));
		
		// Search group setup
		JLabel label = new JLabel("Szukaj telefonu:");;
        JTextField textField = new JTextField();
        JButton findButton = new JButton("Szukaj");
        
        // Button listener for search
        findButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String queryParm = textField.getText();
        		
        		dispose();
        		
        		new app(queryParm).setVisible(true);
        	}
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createSequentialGroup()
            .addComponent(label)
            .addGroup(layout.createParallelGroup(Alignment.LEADING)
                .addComponent(textField))
            .addGroup(layout.createParallelGroup(Alignment.LEADING)
                .addComponent(findButton))
        );
       
        layout.setVerticalGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                    .addComponent(label)
                    .addComponent(textField)
                    .addComponent(findButton))
        );
		
		// Window settings
		setTitle("Mobile Phone Ontology");
		setSize(1000, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public app(String str) {	
		// Search group setup
		JLabel label = new JLabel("Szukaj telefonu:");;
        JTextField textField = new JTextField();
        JButton findButton = new JButton("Szukaj");
        JLabel j = new JLabel("");
        
        textField.setText(str);
        
        // Button listener for search
        findButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		String queryParm = textField.getText();
        		
        		dispose();
        		
        		new app(queryParm).setVisible(true);
        	}
        });
        
        
		
		// Fill data
        
        
        ArrayList<String> tele = new ArrayList<String>();
        String el;
        String all = "";
        int i = 0;
        for(Model m : modelList){
        	for(Resource r : m.subjects()){
        		Model name = m.filter(r, vocabulary.NAME, null);
        		el = Models.objectString(name).orElse("");
        		if(el.contains(str)){
        			System.out.println("Telefon - " + el);
        			tele.add("Telefon - " + el);
        			all+= el + "; ";
        			i++;
                	if(i==10) break;
        		}
        		
        	}        	/*if(m.filter(vocabulary.NAME,null,null).contains(str)){
        		int xxa = 1;
        	}
        	*/
        	if(i==10)break;
        }
        
        JList<String> elements = new JList<>(tele.toArray(new String[0]));
        JScrollPane scroll = new JScrollPane(elements);
        
        j.setText(all);
        
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout.createSequentialGroup()
            .addComponent(label)
            .addGroup(layout.createParallelGroup(Alignment.LEADING)
                .addComponent(textField)
                .addComponent(scroll))
            .addGroup(layout.createParallelGroup(Alignment.LEADING)
                .addComponent(findButton))
        );
       
        layout.setVerticalGroup(layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(Alignment.BASELINE)
                .addComponent(label)
                .addComponent(textField)
                .addComponent(findButton))
            .addGroup(layout.createParallelGroup(Alignment.BASELINE)
        		.addComponent(scroll))
        );
        
        
        
        //while(iter.hasNext()) {
        //	Resource res = iter.nextResource();
        //}
		
		// Window settings
		setTitle("Mobile Phone Ontology");
		setSize(1000, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public static void main(String[] args) throws IOException, JAXBException{
		new app().setVisible(true);
	}
}