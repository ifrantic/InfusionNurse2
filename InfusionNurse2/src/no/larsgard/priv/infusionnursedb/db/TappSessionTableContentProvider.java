package no.larsgard.priv.infusionnursedb.db;

import java.util.Arrays;
import java.util.HashSet;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;



public class TappSessionTableContentProvider extends ContentProvider {

	  // database
	  private MyInfusionNurse2DatabaseHelper database;

	  // Used for the UriMacher
	  private static final int SESSION = 10;
	  private static final int SESSION_ID = 20;
	  
	  private static final String AUTHORITY = "no.priv.larsgard.infusionnurse2.contentprovider";
	  
	  private static final String BASE_PATH = "sessions";
	  public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY
	      + "/" + BASE_PATH);

	  public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
	      + "/sessions";
	  public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE
	      + "/session";

	  private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);
	  static {
	    sURIMatcher.addURI(AUTHORITY, BASE_PATH, SESSION);
	    sURIMatcher.addURI(AUTHORITY, BASE_PATH + "/#", SESSION_ID);
	  }
	  
		@Override
		public boolean onCreate() {
			database=new MyInfusionNurse2DatabaseHelper(getContext());
			return false;
		}
		

		@Override
		public Cursor query(Uri uri, String[] projection, String selection,
				String[] selectionArgs, String sortOrder) {
			
			SQLiteQueryBuilder queryTapp=new SQLiteQueryBuilder();
			
			
			checkColumns(projection);
			
			queryTapp.setTables(TappSessionTable.TAPP_SESSION_TABLE);
			
			int uriType=sURIMatcher.match(uri);
			switch(uriType){
			case SESSION:
				break;
			case SESSION_ID:
				queryTapp.appendWhere(TappSessionTable.COLUMN_ID+"="
						+uri.getLastPathSegment());
				break;
				default:
					throw new IllegalArgumentException("Ukjent URI: " +uri);
					
			}
			
			SQLiteDatabase db= database.getWritableDatabase();
			Cursor cursor=queryTapp.query(db, projection, selection, selectionArgs, null, null, sortOrder);
			
			// Make sure that potential listeners are getting notified
			cursor.setNotificationUri(getContext().getContentResolver(),uri);
			
			return cursor;
		}
		

		@Override
		public String getType(Uri uri) {
			
			return null;
		}

		@Override
		public Uri insert(Uri uri, ContentValues values) {
			
			int uriType=sURIMatcher.match(uri);
			
			SQLiteDatabase sqlDB=database.getWritableDatabase();
			int rowsDeleted=0;
			long id=0;
			switch(uriType){
			case SESSION:
				id=sqlDB.insert(TappSessionTable.TAPP_SESSION_TABLE, null, values);
				break;
				default:
					throw new IllegalArgumentException("Ukjent URI: "+uri);
			}
			
			getContext().getContentResolver().notifyChange(uri, null);
			
			return Uri.parse(BASE_PATH+"/"+id);
		}
		
		
		  @Override
		  public int delete(Uri uri, String selection, String[] selectionArgs) {
		    int uriType = sURIMatcher.match(uri);
		    SQLiteDatabase sqlDB = database.getWritableDatabase();
		    int rowsDeleted = 0;
		    switch (uriType) {
		    case SESSION:
		      rowsDeleted = sqlDB.delete(TappSessionTable.TAPP_SESSION_TABLE, selection,
		          selectionArgs);
		      break;
		    case SESSION_ID:
		      String id = uri.getLastPathSegment();
		      if (TextUtils.isEmpty(selection)) {
		        rowsDeleted = sqlDB.delete(TappSessionTable.TAPP_SESSION_TABLE,
		            TappSessionTable.COLUMN_ID + "=" + id, 
		            null);
		      } else {
		        rowsDeleted = sqlDB.delete(TappSessionTable.TAPP_SESSION_TABLE,
		        		TappSessionTable.TAPP_SESSION_TABLE + "=" + id 
		            + " and " + selection,
		            selectionArgs);
		      }
		      break;
		    default:
		      throw new IllegalArgumentException("Ukjent URI: " + uri);
		    }
		    getContext().getContentResolver().notifyChange(uri, null);
		    return rowsDeleted;
		  }






		  @Override
		  public int update(Uri uri, ContentValues values, String selection,
		      String[] selectionArgs) {

		    int uriType = sURIMatcher.match(uri);
		    SQLiteDatabase sqlDB = database.getWritableDatabase();
		    int rowsUpdated = 0;
		    switch (uriType) {
		    case SESSION:
		      rowsUpdated = sqlDB.update(TappSessionTable.TAPP_SESSION_TABLE, 
		          values, 
		          selection,
		          selectionArgs);
		      break;
		    case SESSION_ID:
		      String id = uri.getLastPathSegment();
		      if (TextUtils.isEmpty(selection)) {
		        rowsUpdated = sqlDB.update(TappSessionTable.TAPP_SESSION_TABLE, 
		            values,
		            TappSessionTable.COLUMN_ID + "=" + id, 
		            null);
		      } else {
		        rowsUpdated = sqlDB.update(TappSessionTable.TAPP_SESSION_TABLE, 
		            values,
		            TappSessionTable.TAPP_SESSION_TABLE + "=" + id 
		            + " and " 
		            + selection,
		            selectionArgs);
		      }
		      break;
		    default:
		      throw new IllegalArgumentException("Unknown URI: " + uri);
		    }
		    getContext().getContentResolver().notifyChange(uri, null);
		    return rowsUpdated;
		  }

	
	  private void checkColumns(String[] projection) {
		    String[] available = { TappSessionTable.TAPP_SESSION_TABLE,
		    		TappSessionTable.COLUMN_COUNT_TAPP};
		    if (projection != null) {
		      HashSet<String> requestedColumns = new HashSet<String>(Arrays.asList(projection));
		      HashSet<String> availableColumns = new HashSet<String>(Arrays.asList(available));
		      // Check if all columns which are requested are available
		      if (!availableColumns.containsAll(requestedColumns)) {
		        throw new IllegalArgumentException("Unknown columns in projection");
		      }
		    }
		  }
}
