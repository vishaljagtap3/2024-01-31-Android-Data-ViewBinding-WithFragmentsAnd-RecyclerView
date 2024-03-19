package `in`.bitcode.fagments6

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment

class AddPasswordFragment : Fragment() {

    private lateinit var edtUsername : EditText
    private lateinit var edtPassword : EditText
    private lateinit var btnSavePassword : Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.add_password_fragment, null)
        initViews(view)
        initListeners()
        return view;
    }

    interface OnPasswordAddedListener {
        fun onPasswordAdded(username : String, password : String)
    }
    var onPasswordAddedListener : OnPasswordAddedListener? = null

    private fun initListeners() {
        btnSavePassword.setOnClickListener {
            /*if(onPasswordAddedListener != null) {
                onPasswordAddedListener!!.onPasswordAdded(
                    edtUsername.text.toString(),
                    edtPassword.text.toString()
                )
            }*/
            onPasswordAddedListener?.onPasswordAdded(
                edtUsername.text.toString(),
                edtPassword.text.toString()
            )

            /*parentFragmentManager.beginTransaction()
                .remove(this@AddPasswordFragment)
                .commit()*/
            parentFragmentManager.popBackStack()
        }
    }

    private fun initViews(view : View) {
        edtUsername = view.findViewById(R.id.edtUsername)
        edtPassword = view.findViewById(R.id.edtPassword)
        btnSavePassword = view.findViewById(R.id.btnSavePassword)
    }
}