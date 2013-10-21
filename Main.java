package com.example.tasklist;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class Main extends Activity implements OnClickListener, OnKeyListener {
	
	EditText TextEdit;
	Button BAdd;
	ListView List;
	ArrayList<String> Inserts;
	ArrayAdapter<String> Adapt;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		TextEdit = (EditText)findViewById(R.id.TextEdit);
		BAdd = (Button)findViewById(R.id.BAdd);
		List = (ListView)findViewById(R.id.List);
		BAdd.setOnClickListener(this);
		TextEdit.setOnKeyListener(this);
		
		Inserts = new ArrayList<String>();
		Adapt = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Inserts);
		List.setAdapter(Adapt);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuItem item;
		item = menu.add("delete");
		return true;
		
	}
	
	private void deleteItem(int Id)
	{
		if (Id >= 0)
		{
			String itemName = (String)List.getItemAtPosition(Id);
			Toast.makeText(getApplicationContext(), itemName+"deleted", Toast.LENGTH_SHORT).show();
			this.Inserts.remove(Id);
			Adapt.notifyDataSetChanged();
		}
	}

	@Override
	public boolean onKey(View v, int keyCode, KeyEvent event) {
		if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_DPAD_CENTER)
		{
			this.Add(this.TextEdit.getText().toString());
		}
		return false;
	}

	@Override
	public void onClick(View v) {
		if(v == this.BAdd)
			this.Add(this.TextEdit.getText().toString());
	}

	private void Add(String task)
	{
		if (task.length() > 0)
		{
			this.Inserts.add(task);
			this.Adapt.notifyDataSetChanged();
			this.TextEdit.setText("");
		}
	}
	


}
