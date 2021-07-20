package com.example.library.fragments.add

import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.library.R
import com.example.library.libdb.Lib
import com.example.library.libdb.LibViewModel

class AddFragment : Fragment() {

    private lateinit var mLibViewModel: LibViewModel
    private lateinit var bookName: EditText
    private lateinit var bookAuthor: EditText
    private lateinit var bookIsbn: EditText
    private lateinit var  addBookToDb: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)
        addBookToDb = view.findViewById(R.id.add_book_to_db)
        bookName = view.findViewById(R.id.book_name)
        bookAuthor = view.findViewById(R.id.book_author)
        bookIsbn = view.findViewById(R.id.book_isbn)
        mLibViewModel = ViewModelProvider(this).get(LibViewModel::class.java)

        addBookToDb.setOnClickListener{
            insertDataToDatabase()
        }
        return view
    }

    private fun insertDataToDatabase() {

        val bookName = bookName.text.toString()
        val authorName = bookAuthor.text.toString()
        val isbnNo = bookIsbn.text.toString()

        if(checkInput(bookName, authorName, isbnNo)){   // to check the book data
            val book = Lib(0, bookName, authorName, isbnNo)

            mLibViewModel.addBook(book)     // adding data to database
            Toast.makeText(requireContext(), "BOOK ADDED SUCCESSFULLY", Toast.LENGTH_SHORT).show()

            findNavController().navigate(R.id.action_addFragment_to_listFragment)   //back to listFragment
        }
        else(
                Toast.makeText(requireContext(), "ALL FIELDS ARE MANDATORY", Toast.LENGTH_SHORT).show()
        )
    }

    private fun checkInput(bookName: String, authorName: String, isbnNo: String): Boolean{
        return !(TextUtils.isEmpty(bookName) && TextUtils.isEmpty(authorName) && TextUtils.isEmpty(isbnNo))
    }
}