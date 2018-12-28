/*
 Copyright (c) 2017 CommonsWare, LLC
 Licensed under the Apache License, Version 2.0 (the "License"); you may not
 use this file except in compliance with the License. You may obtain	a copy
 of the License at http://www.apache.org/licenses/LICENSE-2.0. Unless required
 by applicable law or agreed to in writing, software distributed under the
 License is distributed on an "AS IS" BASIS,	WITHOUT	WARRANTIES OR CONDITIONS
 OF ANY KIND, either express or implied. See the License for the specific
 language governing permissions and limitations under the License.

 Covered in detail in the book _The Busy Coder's Guide to Android Development_
 https://commonsware.com/Android
 */

package nam.tran.flatform.database.core;

import android.content.Context;
import android.text.Editable;

import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

/**
 * SupportSQLiteOpenHelper.Factory implementation, for use with Room
 * and similar libraries, that supports SQLCipher for Android.
 */
public class SafeHelperFactory implements SupportSQLiteOpenHelper.Factory {
  public static final String POST_KEY_SQL_MIGRATE = "PRAGMA cipher_migrate;";
  public static final String POST_KEY_SQL_V3 = "PRAGMA cipher_compatibility = 3;";

  final private char[] passphrase;
  final private String postKeySql;

  /**
   * Creates a SafeHelperFactory from an Editable, such as what you get by
   * calling getText() on an EditText.
   *
   * The Editable will be cleared as part of this call.
   *
   * @param editor the user's supplied passphrase
   * @return a SafeHelperFactory
   */
  public static SafeHelperFactory fromUser(Editable editor) {
    return fromUser(editor, null);
  }

  /**
   * Creates a SafeHelperFactory from an Editable, such as what you get by
   * calling getText() on an EditText.
   *
   * The Editable will be cleared as part of this call.
   *
   * @param editor the user's supplied passphrase
   * @param postKeySql optional SQL to be executed after database has been
   *                    "keyed" but before any other database access is performed
   * @return a SafeHelperFactory
   */
  public static SafeHelperFactory fromUser(Editable editor, String postKeySql) {
    char[] passphrase=new char[editor.length()];
    SafeHelperFactory result;

    editor.getChars(0, editor.length(), passphrase, 0);

    try {
      result=new SafeHelperFactory(passphrase, postKeySql);
    }
    finally {
      editor.clear();
    }

    return(result);
  }

  /**
   * Changes the passphrase associated with this database. The
   * char[] is *not* cleared by this method -- please zero it
   * out if you are done with it.
   *
   * This will not encrypt an unencrypted database. Please use the
   * encrypt() method for that.
   *
   * @param db the database to rekey
   * @param passphrase the new passphrase to use
   */
  public static void rekey(SupportSQLiteDatabase db, char[] passphrase) {
    if (db instanceof Database) {
      ((Database)db).rekey(passphrase);
    }
    else {
      throw new IllegalArgumentException("Database is not from CWAC-SafeRoom");
    }
  }

  /**
   * Changes the passphrase associated with this database. The supplied
   * Editable is cleared as part of this operation.
   *
   * This will not encrypt an unencrypted database. Please use the
   * encrypt() method for that.
   *
   * @param db the database to rekey
   * @param editor source of passphrase, presumably from a user
   */
  public static void rekey(SupportSQLiteDatabase db, Editable editor) {
    if (db instanceof Database) {
      ((Database)db).rekey(editor);
    }
    else {
      throw new IllegalArgumentException("Database is not from CWAC-SafeRoom");
    }
  }

  /**
   * Standard constructor.
   *
   * Note that the passphrase supplied here will be filled in with zeros after
   * the database is opened. Ideally, you should not create additional copies
   * of this passphrase, particularly as String objects.
   *
   * If you are using an EditText to collect the passphrase from the user,
   * call getText() on the EditText, and pass that Editable to the
   * SafeHelperFactory.fromUser() factory method.
   *
   * @param passphrase user-supplied passphrase to use for the database
   */
  public SafeHelperFactory(char[] passphrase) {
    this(passphrase, null);
  }

  /**
   * Standard constructor.
   *
   * Note that the passphrase supplied here will be filled in with zeros after
   * the database is opened. Ideally, you should not create additional copies
   * of this passphrase, particularly as String objects.
   *
   * If you are using an EditText to collect the passphrase from the user,
   * call getText() on the EditText, and pass that Editable to the
   * SafeHelperFactory.fromUser() factory method.
   *
   * @param passphrase user-supplied passphrase to use for the database
   * @param postKeySql optional callback to be called after database has been
   *                    "keyed" but before any database access is performed
   */
  public SafeHelperFactory(char[] passphrase, String postKeySql) {
    this.passphrase=passphrase;
    this.postKeySql=postKeySql;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public SupportSQLiteOpenHelper create(
    SupportSQLiteOpenHelper.Configuration configuration) {
    return(create(configuration.context, configuration.name,
      configuration.callback));
  }

  public SupportSQLiteOpenHelper create(Context context, String name,
                                        SupportSQLiteOpenHelper.Callback callback) {
    return(new Helper(context, name, callback, passphrase, postKeySql));
  }
}
