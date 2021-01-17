package com.godbalance.ads06

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.zxing.integration.android.IntentIntegrator
import com.jakewharton.threetenabp.AndroidThreeTen
import kotlinx.android.synthetic.main.activity_search.*
import kotlinx.android.synthetic.main.holder.view.*
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter

class SearchActivity : AppCompatActivity() {

    lateinit var mRecyclerView: RecyclerView
    lateinit var mDatabase: DatabaseReference
    lateinit var FirebaseRecyclerAdapter : FirebaseRecyclerAdapter<FDB , FdbfdbViewHolder>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        AndroidThreeTen.init(this)

        val pk = intent.getStringExtra("PK")

        btn_scan_search.setOnClickListener {

            input_search.setText("ШК").toString()
            val scanner = IntentIntegrator(this)
            scanner.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
            scanner.setBeepEnabled(false)
            scanner.initiateScan()
        }

        val atributes = arrayOf("Поиск по ЛМ","Поиск по ШК","Поиск по адресу")

        spnr_arg.adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,atributes)
        spnr_arg.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

                //
            }

            override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
            ) {
                when (position) {
                    0 -> input_search.hint = "Введите ЛМ"
                    1 -> input_search.hint = "Введите ШК"
                    2 -> input_search.hint = "Введите адрес"
                }

                //
            }
        }

        mDatabase = FirebaseDatabase.getInstance().getReference("FDB06")

        mRecyclerView = findViewById(R.id.rcl_view)

        btn_add_activity.setOnClickListener{
//            По идее этот блок тоже нужно удалять

//            val positionSpinner =spnr_arg.selectedItem.toString()



//            Блок if нужно будет полностью удалить
//            а блок else открыть и оставить
//            if (positionSpinner == "Поиск по адресу"){
//                intent = Intent(this, AddActivity::class.java)
//                intent.putExtra("PK", pk)
//                intent.putExtra("ADRES", input_search.text.toString().trim())
//                startActivity(intent)
//            }else {
                intent = Intent(this, AddActivity::class.java)
                intent.putExtra("PK", pk)
                startActivity(intent)
//            }
        }

        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(this)

        input_search.addTextChangedListener(object :TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                val searchText = input_search.text.toString()

                loadFirebaseDatabase(searchText)
            }
        })
    }

    private fun loadFirebaseDatabase(searchText: String) {

        if (searchText.isEmpty()){

            FirebaseRecyclerAdapter.cleanup()
            mRecyclerView.adapter = FirebaseRecyclerAdapter
        }else {

            val positionSpinner =spnr_arg.selectedItem.toString()
            var searchArg = ""
            if (positionSpinner == "Поиск по ЛМ"){
                searchArg = "lm"
            }
            if (positionSpinner == "Поиск по ШК"){
                searchArg = "barcode"
            }
            if (positionSpinner == "Поиск по адресу"){
                searchArg = "adres"
            }

            val firebaseSearchQuery = mDatabase.orderByChild(searchArg).startAt(searchText).endAt(searchText+"\uf8ff")

            FirebaseRecyclerAdapter = object : FirebaseRecyclerAdapter<FDB, FdbfdbViewHolder>(
                    FDB::class.java,
                    R.layout.holder,
                    FdbfdbViewHolder::class.java,
                    firebaseSearchQuery
            ) {
                @SuppressLint("SetTextI18n", "RestrictedApi")
                override fun populateViewHolder(
                        viewHolder: FdbfdbViewHolder,
                        model: FDB?,
                        position: Int
                ) {
                    viewHolder.mView.res_adres.text = "Адрес: " + model?.adres
                    viewHolder.mView.res_date.text = "Изменено: " + model?.date
                    viewHolder.mView.res_bc.text = "ШК: " + model?.barcode
                    viewHolder.mView.res_lm.text = "ЛМ: " + model?.lm
                    viewHolder.mView.res_name.text = model?.name
                    viewHolder.mView.res_stock.text = model?.stock + " шт"
                    viewHolder.mView.res_pk.text = "Кем: " + model?.pk

                    viewHolder.mView.btn_more_hold.setOnClickListener {
                        val popupMenu = PopupMenu(this@SearchActivity,viewHolder.mView.btn_more_hold)
                        popupMenu.menuInflater.inflate(R.menu.menu_holder,popupMenu.menu)
                        popupMenu.setOnMenuItemClickListener { item ->
                            when (item.itemId){
                                R.id.menu_btn_edit ->{

                                    val bui = AlertDialog.Builder(this@SearchActivity)
                                    bui.setTitle("Изменение запаса на адресе:")
                                    val inputUpd = EditText(this@SearchActivity)
                                    inputUpd.hint = "Введите количество"
                                    bui.setView(inputUpd,60,50,60,0)
                                    bui.setPositiveButton("Изменить"){dialog, which ->

                                        val upd = inputUpd.text.toString().trim()
                                        getRef(position).ref.child("pk").setValue(intent.getStringExtra("PK"))
                                        getRef(position).ref.child("date").setValue(
                                            LocalDateTime.now().format(
                                                DateTimeFormatter.ISO_LOCAL_DATE).toString().trim())
                                        getRef(position).ref.child("stock").setValue(upd).addOnCompleteListener {
                                            Toast.makeText(this@SearchActivity,"Изменено",Toast.LENGTH_SHORT).show()
                                        }
                                    }
                                    bui.show()
                                }
                                R.id.menu_btn_del ->{

                                    val bui = AlertDialog.Builder(this@SearchActivity)
                                    bui.setTitle("Внимание!")
                                    bui.setMessage("Вы хотите удалить товар:" +
                                            "\n${viewHolder.mView.res_name.text}" +
                                            "\n"+
                                            "\nC адреса: ${model?.adres}?")
                                    bui.setPositiveButton("Да"){dialog, which ->
                                        getRef(position).ref.removeValue().addOnCompleteListener {
                                            Toast.makeText(this@SearchActivity,"Удалено",Toast.LENGTH_SHORT).show()
                                        }
                                        loadFirebaseDatabase(searchText)
                                    }

                                    bui.show()

                                }
                                R.id.menu_btn_send ->{

                                    val sendIntent: Intent = Intent().apply {
                                        action = Intent.ACTION_SEND
                                        putExtra(Intent.EXTRA_TEXT, "${viewHolder.mView.res_lm.text}" +
                                                "\n${viewHolder.mView.res_name.text}" +
                                                "\n${viewHolder.mView.res_adres.text}" +
                                                "\n${viewHolder.mView.res_stock.text}" +
                                                "\n${viewHolder.mView.res_date.text} " +
                                                "\n${viewHolder.mView.res_pk.text}")
                                        type = "text/plain"
                                    }

                                    val shareIntent = Intent.createChooser(sendIntent, null)
                                    startActivity(shareIntent)


                                }


                            }
                            true
                        }
                        popupMenu.show()
                    }

                }
            }

            mRecyclerView.adapter = FirebaseRecyclerAdapter
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK){

            val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
            if (result != null) {
                if (result.contents == null) {
                    Toast.makeText(this, "Ошибка", Toast.LENGTH_SHORT).show()
                } else {

                    if (input_search.text.toString() == "ШК"){
                        input_search.setText(result.contents)
                        Toast.makeText(this, "Отсканированно", Toast.LENGTH_SHORT).show()}
                }
            } else {
                super.onActivityResult(requestCode, resultCode, data)
            }

        }
    }

    class FdbfdbViewHolder(var mView: View): RecyclerView.ViewHolder(mView)
}

