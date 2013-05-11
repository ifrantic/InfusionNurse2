package no.larsgard.priv.infusionnursedb.db;

import android.database.sqlite.SQLiteDatabase;

public class TappSessionTable {
	
		//Tabell 
		public static final String TAPP_SESSION_TABLE="TAPP_SESSION";
		
		// tabell-kolonne navn 
	    public static final String COLUMN_ID = "_id";
	    public static final String COLUMN_SESSION_START = "TAPP_SESSION_START";
	    public static final String COLUMN_SESSION_END="TAPP_SESSION_END";
	    public static final String COLUMN_COUNT_TAPP="COUNT_TAPP";
	  
	    
	    public static final String CREATE_TAPP_SESSION_TABLE = "CREATE TABLE " + TAPP_SESSION_TABLE + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," + COLUMN_SESSION_START + " DATETIME, "
                + COLUMN_SESSION_END + " DATETIME, " + COLUMN_COUNT_TAPP + " INT "
               + ")";
	

		public static void onCreate(SQLiteDatabase db) {
			// oppretter tabellen
			
		        db.execSQL(CREATE_TAPP_SESSION_TABLE);
			
			
		}

		public static void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// Upgrading database
		
		        db.execSQL("DROP TABLE IF EXISTS " + TAPP_SESSION_TABLE);
		 
		        // Create tables again
		        onCreate(db);
			
		}
}
