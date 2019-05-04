package com.example.sqlite; // replace with the package name you have

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

// The class is extending SQLiteOpenHelper
public class DatabaseManager extends  SQLiteOpenHelper{

    // here we are defining all the Strings that is required for our database
    // for example databasename, table name and column names.
    private static final String DATABASE_NAME = "QuickFitDB";
    private static final int DATABASE_VERSION = 1;
    // user_account table
    private static final String TABLE1_NAME = "user_account";
    private static final String COLUMN1_USERID = "user_id";
    private static final String COLUMN1_PASSWORD = "password";
    private static final String COLUMN1_FIRSTNAME = "first_name";
    private static final String COLUMN1_LASTNAME = "last_name";
    private static final String COLUMN1_EMAIL = "email";
    private static final String COLUMN1_GENDER = "gender";
    private static final String COLUMN1_DATEOFBIRTH = "dob";
    // user_body table
    private static final String TABLE2_NAME = "user_body";
    private static final String COLUMN2_WEIGHT = "weight";
    private static final String COLUMN2_HEIGHT = "height";
    private static final String COLUMN2_BMI = "bmi";
    private static final String COLUMN2_DATE = "date";
    private static final String COLUMN2_USER_ID = "user_id";
    // user_activity table
    private static final String TABLE3_NAME = "user_activity";
    private static final String COLUMN3_STEPS = "steps";
    private static final String COLUMN3_CALORIESBURNED = "calories_burned";
    private static final String COLUMN3_DISTANCE = "distance";
    private static final String COLUMN3_DATE = "date";
    private static final String COLUMN3_USERID = "user_id";
    private static final String COLUMN3_CALORIESINTAKE = "calories_intake";
    private static final String COLUMN3_WATERINTAKE= "water_intake";
    // exercise_table
    private static final String TABLE4_NAME = "exercise_table";
    private static final String COLUMN4_NAME = "name";
    private static final String COLUMN4_CALORIES = "calories";
    private static final String COLUMN4_LINK = "link";
    private static final String COLUMN4_EXERCISEID = "exercise_id";
    // recipe_table
    private static final String TABLE5_NAME = "recipe_table";
    private static final String COLUMN5_NAME = "name";
    private static final String COLUMN5_CALORIES = "calories";
    private static final String COLUMN5_LINK = "link";
    private static final String COLUMN5_RECIPEID = "recipe_id";
    private static final String COLUMN5_PROTEINS = "proteins";
    private static final String COLUMN5_TOTALFAT = "total_fat";
    private static final String COLUMN5_CARBS = "carbohydrates";

    /*
      We need to call the super i.e. parent class constructor
      And we need to pass 4 parameters
      1. Context context -> It is the context object we will get it
      from the activity while creating the instance
      2. String DATABASE_NAME -> It is the name of the database and
      here we are passing the constant that we already defined
      3. CursorFactory cursorFactory -> If we want a cursor to be
      initialized on the creation we can use cursor factory, it is optional
      and that is why we passed null here
      4. int version -> It is an int defining our database version
    */

    DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase){
             //The query to create the tables

            // user_account table
            String sql_account = "CREATE TABLE IF NOT EXISTS " + TABLE1_NAME + " (\n" +
                    " " + COLUMN1_USERID + " TEXT NOT NULL CONSTRAINT user_account_pk PRIMARY KEY,\n" +
                    " " + COLUMN1_PASSWORD + " varchar(30) NOT NULL, \n" +
                    " " + COLUMN1_EMAIL + " varchar(50) NOT NULL, \n" +
                    " " + COLUMN1_FIRSTNAME + " varchar(30) NOT NULL, \n" +
                    " " + COLUMN1_LASTNAME + " varchar(30) NOT NULL, \n" +
                    " " + COLUMN1_GENDER + " varchar(10) NOT NULL, \n" +
                    " " + COLUMN1_DATEOFBIRTH + " datetime NOT NULL" +
                    ");";
            // user_body table
            String sql_body = "CREATE TABLE IF NOT EXISTS " + TABLE2_NAME + " (\n" +
                    " " + COLUMN2_USER_ID + " TEXT NOT NULL,\n" +
                    " " + COLUMN2_DATE + " datetime NOT NULL, \n" +
                    " " + COLUMN2_HEIGHT + " REAL NOT NULL, \n" +
                    " " + COLUMN2_WEIGHT + " REAL NOT NULL, \n" +
                    " " + COLUMN2_BMI + " REAL NOT NULL, \n" +
                    " " + "CONSTRAINT user_body_pk PRIMARY KEY(" + COLUMN2_DATE + ", " + COLUMN2_USER_ID + " )" +
                    ");";
            // user_activity table
            String sql_activity = "CREATE TABLE IF NOT EXISTS " + TABLE3_NAME + " (\n" +
                    " " + COLUMN3_USERID + " TEXT NOT NULL,\n" +
                    " " + COLUMN3_DATE + " datetime NOT NULL, \n" +
                    " " + COLUMN3_STEPS + " INTEGER NOT NULL, \n" +
                    " " + COLUMN3_DISTANCE + " REAL NOT NULL, \n" +
                    " " + COLUMN3_CALORIESBURNED + " INTEGER NOT NULL, \n" +
                    " " + COLUMN3_CALORIESINTAKE + " INTEGER NOT NULL, \n" +
                    " " + COLUMN3_WATERINTAKE + " INTEGER NOT NULL, \n" +
                    " " + "CONSTRAINT user_activity_pk PRIMARY KEY(" + COLUMN3_DATE + ", " + COLUMN3_USERID + " )" +
                    ");";
            // exercise_table
            String sql_exercise = "CREATE TABLE IF NOT EXISTS " + TABLE4_NAME + " (\n" +
                    " " + COLUMN4_EXERCISEID + " INTEGER NOT NULL CONSTRAINT exercise_table_pk PRIMARY KEY AUTOINCREMENT,\n" +
                    " " + COLUMN4_NAME + " varchar(30) NOT NULL, \n" +
                    " " + COLUMN4_LINK + " TEXT NOT NULL, \n" +
                    " " + COLUMN4_CALORIES + " INTEGER NOT NULL" +
                    ");";
            // recipe_table
            String sql_recipe = "CREATE TABLE IF NOT EXISTS " + TABLE5_NAME + " (\n" +
                    " " + COLUMN5_RECIPEID + " INTEGER NOT NULL CONSTRAINT recipe_table_pk PRIMARY KEY AUTOINCREMENT,\n" +
                    " " + COLUMN5_NAME + " varchar(50) NOT NULL, \n" +
                    " " + COLUMN5_LINK + " TEXT NOT NULL, \n" +
                    " " + COLUMN5_PROTEINS + " REAL NOT NULL, \n" +
                    " " + COLUMN5_TOTALFAT + " REAL NOT NULL, \n" +
                    " " + COLUMN5_CARBS + " REAL NOT NULL, \n" +
                    " " + COLUMN5_CALORIES + " INTEGER NOT NULL" +
                    ");";

            // Executing the string to create the table
            sqLiteDatabase.execSQL(sql_account);
            sqLiteDatabase.execSQL(sql_body);
            sqLiteDatabase.execSQL(sql_activity);
            sqLiteDatabase.execSQL(sql_exercise);
            sqLiteDatabase.execSQL(sql_recipe);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1){
        // Just dropping and creating the tables

        String sql_drop_account = "DROP TABLE IF EXISTS " + TABLE1_NAME + ";";
        String sql_drop_body = "DROP TABLE IF EXISTS " + TABLE2_NAME + ";";
        String sql_drop_activity = "DROP TABLE IF EXISTS " + TABLE3_NAME + ";";
        String sql_drop_recipe = "DROP TABLE IF EXISTS " + TABLE5_NAME + ";";
        String sql_drop_exercise = "DROP TABLE IF EXISTS " + TABLE4_NAME + ";";

        sqLiteDatabase.execSQL(sql_drop_account);
        sqLiteDatabase.execSQL(sql_drop_body);
        sqLiteDatabase.execSQL(sql_drop_activity);
        sqLiteDatabase.execSQL(sql_drop_exercise);
        sqLiteDatabase.execSQL(sql_drop_recipe);

        onCreate(sqLiteDatabase);
    }

    /*
     * CREATE OPERATION
     * ====================
     * This is the first operation of the CRUD.
     * This method will create a new employee in the table
     * Method is taking all the parameters required
     *
     * Operation is very simple, we just need a content value objects
     * Inside this object we will put everything that we want to insert.
     * So each value will take the column name and the value that is to inserted
     * for the column name we are using the String variables that we defined already
     * And that is why we converted the hardcoded string to variables
     *
     * Once we have the contentValues object with all the values required
     * We will call the method getWritableDatabase() and it will return us the SQLiteDatabase object and we can write on the database using it.
     *
     * With this object we will call the insert method it takes 3 parameters
     * 1. String -> The table name where the value is to be inserted
     * 2. String -> The default values of null columns, it is null here as we don't have any default values
     * 3. ContentValues -> The values that is to be inserted
     *
     * insert() will return the inserted row id, if there is some error inserting the row
     * it will return -1
     *
     * So here we are returning != -1, it will be true of record is inserted and false if not inserted
     * */

    boolean createAccount(String user_id, String password, String email, String first_name, String last_name, String gender, String date_of_birth){
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN1_USERID, user_id);
        contentValues.put(COLUMN1_PASSWORD, password);
        contentValues.put(COLUMN1_EMAIL, email);
        contentValues.put(COLUMN1_FIRSTNAME, first_name);
        contentValues.put(COLUMN1_LASTNAME, last_name);
        contentValues.put(COLUMN1_GENDER, gender);
        contentValues.put(COLUMN1_DATEOFBIRTH, date_of_birth);

        SQLiteDatabase db = getWritableDatabase();
        return db.insert(TABLE1_NAME, null, contentValues) != -1;
    }

    boolean body_entry(String user_id, String date, Float height, Float weight, Float bmi){
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN2_USER_ID, user_id);
        contentValues.put(COLUMN2_DATE, date);
        contentValues.put(COLUMN2_HEIGHT, height);
        contentValues.put(COLUMN2_WEIGHT, weight);
        contentValues.put(COLUMN2_BMI, bmi);

        SQLiteDatabase db = getWritableDatabase();
        return db.insert(TABLE2_NAME, null, contentValues) != -1;
    }

    boolean activity_entry(String user_id, String date, int steps, Float distance, int calories_burned, int calories_intake, int water_intake){
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN3_USERID, user_id);
        contentValues.put(COLUMN3_DATE, date);
        contentValues.put(COLUMN3_STEPS, steps);
        contentValues.put(COLUMN3_DISTANCE, distance);
        contentValues.put(COLUMN3_CALORIESBURNED, calories_burned);
        contentValues.put(COLUMN3_CALORIESINTAKE, calories_intake);
        contentValues.put(COLUMN3_WATERINTAKE, water_intake);

        SQLiteDatabase db = getWritableDatabase();
        return db.insert(TABLE3_NAME, null, contentValues) != -1;
    }


    /*
     * READ OPERATION
     * =================
     * Here we are reading values from the database
     * First we called the getReadableDatabase() method it will return us the SQLiteDatabase instance
     * but using it we can only perform the read operations.
     *
     * We are running rawQuery() method by passing the select query.
     * rawQuery takes two parameters
     * 1. The query
     * 2. String[] -> Arguments that is to be binded -> We use it when we have a where clause in our query to bind the where value
     *
     * rawQuery returns a Cursor object having all the data fetched from database
     * */

    Cursor getAllUsers(){
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE1_NAME, null);
    }

    /*
     * UPDATE OPERATION
     * ==================
     * Here we are performing the update operation. The proecess is same as the Create operation.
     * We are first getting a database instance using getWritableDatabase() method as the operation we need to perform is a write operation
     * Then we have the contentvalues object with the new values
     *
     * to update the row we use update() method. It takes 4 parameters
     * 1. String -> It is the table name
     * 2. ContentValues -> The new values
     * 3. String -> Here we pass the column name = ?, the column we want to use for putting the where clause
     * 4. String[] -> The values that is to be binded with the where clause
     * */
    boolean updateAccount(String user_id, String password, String email, String first_name, String last_name, String gender, String date_of_birth) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COLUMN1_USERID, user_id);
        contentValues.put(COLUMN1_PASSWORD, password);
        contentValues.put(COLUMN1_EMAIL, email);
        contentValues.put(COLUMN1_FIRSTNAME, first_name);
        contentValues.put(COLUMN1_LASTNAME, last_name);
        contentValues.put(COLUMN1_GENDER, gender);
        contentValues.put(COLUMN1_DATEOFBIRTH, date_of_birth);

        return db.update(TABLE1_NAME, contentValues, COLUMN1_USERID + "=?", new String[]{String.valueOf(user_id)}) == 1;
    }


    /*
     * DELETE OPERATION
     * ======================
     *
     * This is the last delete operation.  To delete again we need a writable database using getWritableDatabase()
     * Then we will call the delete method. It takes 3 parameters
     * 1. String -> Table name
     * 2. String -> The where clause passed as columnname = ?
     * 3. String[] -> The values to be binded on the where clause
     * */
    boolean deleteAccount(String user_id) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE1_NAME, COLUMN1_USERID + "=?", new String[]{String.valueOf(user_id)}) == 1;
    }
    boolean deleteRecipe(int recipe_id) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE5_NAME, COLUMN5_RECIPEID + "=?", new String[]{String.valueOf(recipe_id)}) == 1;
    }
    boolean deleteExercise(int exercise_id) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete(TABLE4_NAME, COLUMN4_EXERCISEID + "=?", new String[]{String.valueOf(exercise_id)}) == 1;
    }
}

