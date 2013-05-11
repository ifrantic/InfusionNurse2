package no.larsgard.priv.infusionnursedb.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyInfusionNurse2DatabaseHelper extends SQLiteOpenHelper {

	//Statiske variabler
	
			private static final int DATABASE_VERSION=1;
	
			//Databasenavn
			private static final String DATABASE_NAME="infusionNurseStatistics";
			
			public MyInfusionNurse2DatabaseHelper(Context context){
				super(context, DATABASE_NAME, null, DATABASE_VERSION);
				
			}
			@Override
			public void onCreate(SQLiteDatabase database){
				TappSessionTable.onCreate(database);
				CalculateSpeedSessionTable.onCreate(database);
				ConvertSpeedSessionTable.onCreate(database);
			}


			@Override
			public void onUpgrade(SQLiteDatabase db, int oldVersion,
					int newVersion) {
				TappSessionTable.onUpgrade(db, oldVersion, newVersion);
				CalculateSpeedSessionTable.onUpgrade(db, oldVersion, newVersion);
				ConvertSpeedSessionTable.onUpgrade(db, oldVersion, newVersion);
				
			}
}
