package `in`.bitcode.fagments6

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class PasswordDetailsFragment : BottomSheetDialogFragment(){

    private lateinit var txtUsername : TextView
    private lateinit var txtPassword : TextView
    private lateinit var txtId : TextView
    private lateinit var txtCategory : TextView
    private lateinit var passwordModel: PasswordModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.password_details_fragment, null)

        initViews(view)
        bindDataToViews()

        return view
    }

    private fun bindDataToViews() {
        passwordModel = requireArguments().getSerializable(PasswordModel.KEY_PASSWORD_MODEL) as PasswordModel
        txtId.text = "ID: ${passwordModel.id}"
        txtUsername.text = "Username: ${passwordModel.username}"
        txtPassword.text = "Password: ${passwordModel.password}"
        txtCategory.text =
            when(passwordModel.category) {
                PasswordModel.CATEGORY_SOCIAL_MEDIA -> "Social Media"
                PasswordModel.CATEGORY_EMAIL -> "Email"
                PasswordModel.CATEGORY_ECOMMERCE -> "E-Commerce"
                PasswordModel.CATEGORY_JOB_PORTAL -> "Job Portal"
                else -> "General"
            }
    }

    private fun initViews(view : View) {
        txtId = view.findViewById(R.id.txtId)
        txtUsername = view.findViewById(R.id.txtUsername)
        txtPassword = view.findViewById(R.id.txtPassword)
        txtCategory = view.findViewById(R.id.txtCategory)
    }

}