package com.godbalance.ads06

import android.app.Activity
import android.content.Intent
import android.content.res.Resources
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import com.google.firebase.database.FirebaseDatabase
import com.google.zxing.integration.android.IntentIntegrator
import com.jakewharton.threetenabp.AndroidThreeTen
import kotlinx.android.synthetic.main.activity_add.*
import kotlinx.android.synthetic.main.activity_add_copy.btn_add_firebase
import kotlinx.android.synthetic.main.activity_add_copy.btn_scan_barcode
import kotlinx.android.synthetic.main.activity_add_copy.btn_search_activity
import kotlinx.android.synthetic.main.activity_add_copy.input_adres
import kotlinx.android.synthetic.main.activity_add_copy.input_barcode
import kotlinx.android.synthetic.main.activity_add_copy.input_stock
import kotlinx.android.synthetic.main.activity_add_copy.*
import kotlinx.android.synthetic.main.holder.view.*
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter

class AddActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_copy)

        SpinnerAdapter()
        SpinnerAdapter()

//        Удалить после добавления спинера
//        val adres = intent.getStringExtra("ADRES")
//        Удалить после добавления спинера
//        input_adres.setText(adres)

        val pk = intent.getStringExtra("PK")

        AndroidThreeTen.init(this)

//        Удалить после добавления спинера
//        btn_scan_adres.setOnClickListener {
//
//            input_adres.setText("Адрес").toString()
//            val scanner = IntentIntegrator(this)
//            scanner.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
//            scanner.setBeepEnabled(false)
//            scanner.initiateScan()
//        }

        btn_scan_barcode.setOnClickListener {

            input_barcode.setText("Штрихкод").toString()
            val scanner = IntentIntegrator(this)
            scanner.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
            scanner.setBeepEnabled(false)
            scanner.initiateScan()
        }

        btn_search_activity.setOnClickListener{


            intent = Intent(this, SearchActivity::class.java)
            intent.putExtra("PK", pk)
            startActivity(intent)
        }

        btn_add_firebase.setOnClickListener{

            val positionSpinner =input_adres.selectedItem.toString()

            if (positionSpinner == "Выбрать адрес"){

                Toast.makeText(this, "Вы не указали адрес!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

//            if (input_adres.selectedItem.toString().isEmpty()){
//
////                input_adres.error = "Пустое поле ввода!"
//                Toast.makeText(this, "Вы не указали адрес!", Toast.LENGTH_SHORT).show()
//                return@setOnClickListener
//            }
            if (input_barcode.text.isEmpty()){

                input_barcode.error = "Пустое поле ввода!"
                return@setOnClickListener
            }
            if (input_stock.text.isEmpty()){

                input_stock.error = "Пустое поле ввода!"
                return@setOnClickListener
            } else{

                val nametxt = input_barcode.text.toString().trim()

                try {

                    val name = getString(
                        resources.getIdentifier(
                            "NAME$nametxt",
                            "string",
                            packageName
                        )
                    ).trim()

                    val build = AlertDialog.Builder(this)
                    build.setTitle("Внимание!")
                    build.setMessage(
                        "Разместить:" +
                                "\n" +
                                "\n$name" +
                                "\n" +
                                "\nна адресе: ${input_adres.selectedItem}"
                    )
                    build.setPositiveButton("Да") { dialog, which ->

                        saveFDBFDB()
                    }

                    build.show()
                }catch (e: Resources.NotFoundException){
                    val bui = AlertDialog.Builder(this)
                    bui.setTitle("Товара нет в базе.")
                    bui.setMessage("Отправить информацию Денису " +
                            "\n" +
                            "\nШК: ${input_barcode.text}")
                    val inputError = EditText(this)
                    inputError.hint = "Введите ЛМ"
                    bui.setView(inputError,60,50,60,0)
                    bui.setPositiveButton("Отправить"){dialog, which ->

                        val sendIntent: Intent = Intent().apply {
                            action = Intent.ACTION_SEND
                            putExtra(Intent.EXTRA_TEXT, "Денис привет. У нас ошибка при размещении товара! " +
                                    "\nШК: ${input_barcode.text}" +
                                    "\nЛМ: ${inputError.text}")
                            type = "text/plain"
                        }

                        val shareIntent = Intent.createChooser(sendIntent, null)
                        startActivity(shareIntent)


                    }
                    bui.show()
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK){

            val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
            if (result != null) {
                if (result.contents == null) {

                    Toast.makeText(this, "Ошибка", Toast.LENGTH_SHORT).show()
                } else {
//                    if (input_adres.text.toString() == "Адрес"){
//
//                        input_adres.setText(result.contents)
//                        Toast.makeText(this, "Отсканированно", Toast.LENGTH_SHORT).show()
//                    }
                    if (input_barcode.text.toString() == "Штрихкод"){

                        input_barcode.setText(result.contents)
                        Toast.makeText(this, "Отсканированно", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                super.onActivityResult(requestCode, resultCode, data)
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun saveFDBFDB() {

        val positionSpinner =input_adres.selectedItem.toString()

        val FDB = "FDB06"

        val pk = intent.getStringExtra("PK")
        val date = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE).toString().trim()
        val adres = positionSpinner.trim()
        val barcode = input_barcode.text.toString().trim()
        val name = getString(resources.getIdentifier("NAME$barcode","string", packageName)).trim()
        val lm = getString(resources.getIdentifier("LM$barcode","string", packageName)).trim()
        val reff = FirebaseDatabase.getInstance().getReference(FDB)
        val stock = input_stock.text.toString().trim()

        val result = FDB(adres,barcode, name,lm, date, stock,pk)

//        input_adres.text = null

        input_barcode.text = null
        input_stock.text = null

        reff.push().setValue(result).addOnCompleteListener{

            Toast.makeText(this, "Размещено", Toast.LENGTH_SHORT).show()
            SpinnerAdapter()
        }
    }

     private fun SpinnerAdapter (){


        val atributes = arrayOf("Выбрать адрес","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23")



        input_adres.adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,atributes)
        input_adres.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

                //
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

                //
            }
        }
//        btn_add_firebase.setOnClickListener {
//            val positionSpinner =spinner.selectedItem.toString()
//
//            textView.text = positionSpinner
//        }
    }
}


