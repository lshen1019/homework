package com.zhu.notebook;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends Activity {
	private  Sqlite db = null;
	
	public Sqlite getDb()
	{
		if(db == null)
		{
			db = new Sqlite(MainActivity.this, "my.db", null, 1);
		}
		return db;
	}

    public void saveNote(String note)
    {
    	Sqlite mydb = getDb();
    	mydb.insertNote(note);
    }
    
    public List<String> getNotes()
    {
    	Sqlite mydb = getDb();
    	List<String> list = mydb.getNotes();
    	return list;
    }
    
    public void freshNoteListView()
    {
    	List<String> list = getNotes();
    	ListView listView = (ListView)this.findViewById(R.id.listView1);
        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_expandable_list_item_1,list));
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        freshNoteListView();
        
        Button btn1 = (Button)this.findViewById(R.id.button1);  
        btn1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			      final EditText input = new EditText(MainActivity.this);
			        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
			        builder.setTitle("note").setIcon(android.R.drawable.ic_dialog_info).setView(input)
			                .setNegativeButton("Cancel", null);
			        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

			            public void onClick(DialogInterface dialog, int which) {
			            	saveNote(input.getText().toString());
			            	freshNoteListView();
			             }
			        });
			        builder.show();
			}
		});  

    }
    


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
