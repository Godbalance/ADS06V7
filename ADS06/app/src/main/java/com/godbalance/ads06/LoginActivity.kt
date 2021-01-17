package com.godbalance.ads06

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        // 60049300 Галустян Александр Николаевич
        btn_pk_1.setOnClickListener{
            val pk = "Г.Александр"
            val bui = AlertDialog.Builder(this)
            bui.setTitle("Введите пароль $pk")
            val inputUpd = EditText(this)
            inputUpd.hint = "Password"
            bui.setView(inputUpd,60,50,60,0)
            bui.setPositiveButton("Войти"){dialog, which ->

               
                val pass = "60049300"

                if (inputUpd.text.toString() == pass){
                    intent = Intent(this, SearchActivity::class.java)
                    intent.putExtra("PK", pk)
                    startActivity(intent)
                    Toast.makeText(this,"Здравствуйте $pk!", Toast.LENGTH_SHORT).show()

                }else{
                    Toast.makeText(this,"Ошибка авторизации!", Toast.LENGTH_SHORT).show()
                }

            }
            bui.show()

        }
        // 60064455 Кондратюк Алексей Александрович
        btn_pk_2.setOnClickListener{
            val pk = "К.Алексей"
            val bui = AlertDialog.Builder(this)
            bui.setTitle("Введите пароль $pk")
            val inputUpd = EditText(this)
            inputUpd.hint = "Password"
            bui.setView(inputUpd,60,50,60,0)
            bui.setPositiveButton("Войти"){dialog, which ->
                val pass = "60064455"

                if (inputUpd.text.toString() == pass){
                    intent = Intent(this, SearchActivity::class.java)
                    intent.putExtra("PK", pk)
                    startActivity(intent)
                    Toast.makeText(this,"Здравствуйте $pk!", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this,"Ошибка авторизации!", Toast.LENGTH_SHORT).show()
                }

            }
            bui.show()

        }
        //  60071458 Литвинов Кирилл Владимирович
        btn_pk_3.setOnClickListener{
            val pk = "Л.Кирилл"
            val bui = AlertDialog.Builder(this)
            bui.setTitle("Введите пароль $pk")
            val inputUpd = EditText(this)
            inputUpd.hint = "Password"
            bui.setView(inputUpd,60,50,60,0)
            bui.setPositiveButton("Войти"){dialog, which ->
                val pass = "60071458"

                if (inputUpd.text.toString() == pass){
                    intent = Intent(this, SearchActivity::class.java)
                    intent.putExtra("PK", pk)
                    startActivity(intent)
                    Toast.makeText(this,"Здравствуйте $pk!", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this,"Ошибка авторизации!", Toast.LENGTH_SHORT).show()
                }

            }
            bui.show()

        }
        // 60002752 Малозенова Татьяна Валериевна
        btn_pk_4.setOnClickListener{
            val pk = "М.Татьяна"
            val bui = AlertDialog.Builder(this)
            bui.setTitle("Введите пароль $pk")
            val inputUpd = EditText(this)
            inputUpd.hint = "Password"
            bui.setView(inputUpd,60,50,60,0)
            bui.setPositiveButton("Войти"){dialog, which ->
                val pass = "60002752"

                if (inputUpd.text.toString() == pass){
                    intent = Intent(this, SearchActivity::class.java)
                    intent.putExtra("PK", pk)
                    startActivity(intent)
                    Toast.makeText(this,"Здравствуйте $pk!", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this,"Ошибка авторизации!", Toast.LENGTH_SHORT).show()
                }

            }
            bui.show()

        }
        // 60049437 Трубкин Николай Игоревич
        btn_pk_5.setOnClickListener{
            val pk = "Т.Николай"
            val bui = AlertDialog.Builder(this)
            bui.setTitle("Введите пароль $pk")
            val inputUpd = EditText(this)
            inputUpd.hint = "Password"
            bui.setView(inputUpd,60,50,60,0)
            bui.setPositiveButton("Войти"){dialog, which ->
                val pass = "60049437"

                if (inputUpd.text.toString() == pass){
                    intent = Intent(this, SearchActivity::class.java)
                    intent.putExtra("PK", pk)
                    startActivity(intent)
                    Toast.makeText(this,"Здравствуйте $pk!", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this,"Ошибка авторизации!", Toast.LENGTH_SHORT).show()
                }

            }
            bui.show()

        }

        // 60080454 Турковский Виктор Викторович
        btn_pk_6.setOnClickListener{
            val pk = "Т.Виктор"
            val bui = AlertDialog.Builder(this)
            bui.setTitle("Введите пароль $pk")
            val inputUpd = EditText(this)
            inputUpd.hint = "Password"
            bui.setView(inputUpd,60,50,60,0)
            bui.setPositiveButton("Войти"){dialog, which ->
                val pass = "60080454"

                if (inputUpd.text.toString() == pass){
                    intent = Intent(this, SearchActivity::class.java)
                    intent.putExtra("PK", pk)
                    startActivity(intent)
                    Toast.makeText(this,"Здравствуйте $pk!", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this,"Ошибка авторизации!", Toast.LENGTH_SHORT).show()
                }

            }
            bui.show()

        }

        // 60056886 Ходоско Андрей Александрович
        btn_pk_7.setOnClickListener{
            val pk = "Х.Андрей"
            val bui = AlertDialog.Builder(this)
            bui.setTitle("Введите пароль $pk")
            val inputUpd = EditText(this)
            inputUpd.hint = "Password"
            bui.setView(inputUpd,60,50,60,0)
            bui.setPositiveButton("Войти"){dialog, which ->
                val pass = "60056886"

                if (inputUpd.text.toString() == pass){
                    intent = Intent(this, SearchActivity::class.java)
                    intent.putExtra("PK", pk)
                    startActivity(intent)
                    Toast.makeText(this,"Здравствуйте $pk!", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this,"Ошибка авторизации!", Toast.LENGTH_SHORT).show()
                }

            }
            bui.show()

        }

        // Третьяк Антон Валерьевич 60082984 antontretyack@gmail.com
        btn_pk_8.setOnClickListener{
            val pk = "Т.Антон"
            val bui = AlertDialog.Builder(this)
            bui.setTitle("Введите пароль $pk")
            val inputUpd = EditText(this)
            inputUpd.hint = "Password"
            bui.setView(inputUpd,60,50,60,0)
            bui.setPositiveButton("Войти"){dialog, which ->
                val pass = "60082984"

                if (inputUpd.text.toString() == pass){
                    intent = Intent(this, SearchActivity::class.java)
                    intent.putExtra("PK", pk)
                    startActivity(intent)
                    Toast.makeText(this,"Здравствуйте $pk!", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this,"Ошибка авторизации!", Toast.LENGTH_SHORT).show()
                }

            }
            bui.show()

        }

        // 60062672 Самойленко Алексей Владимирович 09965432647118152146@android-for-work. gserviceaccount.com
        btn_pk_9.setOnClickListener{
            val pk = "С.Алексей"
            val bui = AlertDialog.Builder(this)
            bui.setTitle("Введите пароль $pk")
            val inputUpd = EditText(this)
            inputUpd.hint = "Password"
            bui.setView(inputUpd,60,50,60,0)
            bui.setPositiveButton("Войти"){dialog, which ->
                val pass = "60062672"

                if (inputUpd.text.toString() == pass){
                    intent = Intent(this, SearchActivity::class.java)
                    intent.putExtra("PK", pk)
                    startActivity(intent)
                    Toast.makeText(this,"Здравствуйте $pk!", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this,"Ошибка авторизации!", Toast.LENGTH_SHORT).show()
                }

            }
            bui.show()

        }

//        Юрий Писарев 60095190 yuriy.bau39@gmail.com
        btn_pk_9.setOnClickListener{
            val pk = "П.Юрий"
            val bui = AlertDialog.Builder(this)
            bui.setTitle("Введите пароль $pk")
            val inputUpd = EditText(this)
            inputUpd.hint = "Password"
            bui.setView(inputUpd,60,50,60,0)
            bui.setPositiveButton("Войти"){dialog, which ->
                val pass = "60095190"

                if (inputUpd.text.toString() == pass){
                    intent = Intent(this, SearchActivity::class.java)
                    intent.putExtra("PK", pk)
                    startActivity(intent)
                    Toast.makeText(this,"Здравствуйте $pk!", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this,"Ошибка авторизации!", Toast.LENGTH_SHORT).show()
                }

            }
            bui.show()

        }
    }
}