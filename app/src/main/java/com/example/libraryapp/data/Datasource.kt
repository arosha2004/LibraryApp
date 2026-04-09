package com.example.libraryapp.data

import android.content.Context
import com.example.libraryapp.model.Book
import org.json.JSONArray

class Datasource {
    fun loadBooksFromAssets(context: Context): List<Book> {
        val jsonString: String = try {
            context.assets.open("books.json").bufferedReader().use { it.readText() }
        } catch (e: Exception) {
            e.printStackTrace()
            return emptyList()
        }

        val booksList = mutableListOf<Book>()
        try {
            val jsonArray = JSONArray(jsonString)
            for (i in 0 until jsonArray.length()) {
                val jsonObject = jsonArray.getJSONObject(i)
                val imageName = jsonObject.getString("coverImage")
                
                // Get the integer resource ID dynamically from the string name!
                val resId = context.resources.getIdentifier(imageName, "drawable", context.packageName)
                
                val book = Book(
                    title = jsonObject.getString("title"),
                    author = jsonObject.getString("author"),
                    isbn = jsonObject.getString("isbn"),
                    description = jsonObject.getString("description"),
                    image = if (resId != 0) resId else android.R.drawable.ic_menu_gallery // Fallback if image missing
                )
                booksList.add(book)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return booksList
    }
}
