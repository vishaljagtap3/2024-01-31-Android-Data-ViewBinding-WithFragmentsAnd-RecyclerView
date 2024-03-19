package `in`.bitcode.fagments6

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class PasswordsAdapter(
    private val passwords : ArrayList<PasswordModel>
) : Adapter<PasswordsAdapter.PasswordViewHolder>(){

    interface OnPasswordClickListener {
        fun onPasswordClick(position : Int, passwordModel: PasswordModel)
    }
    var onPasswordClickListener : OnPasswordClickListener? = null

    inner class PasswordViewHolder(view : View) : ViewHolder(view) {
        val txtUsername : TextView
        val txtPassword : TextView
        init {
            txtUsername = view.findViewById(R.id.txtUsername)
            txtPassword = view.findViewById(R.id.txtPassword)

            view.setOnClickListener {
                //add password details fragment
                onPasswordClickListener?.onPasswordClick(
                    adapterPosition,
                    passwords[adapterPosition]
                )
            }
        }
    }

    override fun getItemCount() = passwords.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PasswordViewHolder {
        return PasswordViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.password_view, null)
        )
    }

    override fun onBindViewHolder(holder: PasswordViewHolder, position: Int) {
        holder.txtUsername.text = passwords[position].username
        holder.txtPassword.text = passwords[position].password
    }
}