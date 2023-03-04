package smsviewer.app.crud_example

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.text.SimpleDateFormat
import java.util.*
import java.util.stream.Collectors
import java.util.stream.IntStream

@SuppressLint("SimpleDateFormat")
val SDF = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")

class ExampleRepository(context: Context) : SQLiteOpenHelper(context,
    "example_db.sqlite",
    null,
    1
) {

    @Suppress("unused")
    fun addSamples(): ExampleRepository {
        val count = count()
        addAll(IntStream.range(count, count + 3).boxed().map(this::nth).collect(Collectors.toList()))
        return this
    }

    fun addAll(list: Collection<Example>) {
        writableDatabase.use { list.forEach { e -> addOne(e, it) } }
    }

    private fun addOne(example: Example) {
        writableDatabase.use { addOne(example, it) }
    }

    private fun addOne(example: Example, db: SQLiteDatabase) {
        val values = ContentValues()
        values.put("id", example.getId())
        values.put("name", example.getName())
        db.insert("example", null, values)
    }

    fun delById(id: Int) {
        writableDatabase.use { it.execSQL("delete from example where id = ?", arrayOf(id)) }
    }

    fun delNth(n: Int) {
        getNth(n)?.getId()?.let { delById(it) }
    }

    fun getById(id: Int): Example? {
        readableDatabase.rawQuery("select * from example where id = ?", arrayOf("$id"))
            .use { c: Cursor ->
                if (!c.moveToNext()) return null
                return readSingle(c)
            }
    }

    fun getNth(n: Int): Example? {
        readableDatabase.use { it.rawQuery("select * from example limit 1 offset ?", arrayOf("$n")).use {
            if (!it.moveToNext()) return null
            return readSingle(it)
        } }
    }

    private fun readSingle(c: Cursor): Example? {
        val e = Example()
        c.getColumnIndex("id")
            .takeIf { it >= 0 }
            ?.apply { e.setId(c.getInt(this)) }
        c.getColumnIndex("name")
            .takeIf { it >= 0 }
            ?.apply { e.setName(c.getString(this)) }
        c.getColumnIndex("created")
            .takeIf { it >= 0 }
            ?.apply { e.setCreated(SDF.parse(c.getString(this))) }
        return e
    }

    fun count(): Int {
        readableDatabase.rawQuery("select count(*) from example", arrayOf())
            .use {
                it.moveToNext()
                return it.getInt(0)
            }
    }

    fun addNext() {
        addOne(Example().setName("example " + count()))
    }

    private fun nth(n: Int): Example {
        val npp = n + 1
        return Example()
            .setId(npp)
            .setName("example $npp")
            .setCreated(Date())
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("""
            create table if not exists example
            (
                id      integer   not null primary key autoincrement,
                name    text,
                created timestamp not null default CURRENT_TIMESTAMP
            )
        """.trimIndent())
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("drop table example")
    }
}
