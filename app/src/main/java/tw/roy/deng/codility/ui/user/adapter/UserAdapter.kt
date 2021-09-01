package tw.roy.deng.codility.ui.user.adapter

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import tw.roy.deng.codility.data.model.User
import tw.roy.deng.codility.databinding.ItemUserBinding
import javax.inject.Inject

class UserAdapter @Inject constructor(
    private val context: Context,
    private val onUserClick: ((user: User, position: Int)-> Unit)
) : PagingDataAdapter<User, RecyclerView.ViewHolder>(UserComparator) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return UserViewHolder(ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val user = getItem(position)

        user?.let { user ->
            val viewHolder = holder as UserViewHolder

            Picasso.get().load(user.avatarUrl).into(
                viewHolder.itemUserBinding.avatar,
                object : com.squareup.picasso.Callback {
                    override fun onSuccess() {
                        val source = (viewHolder.itemUserBinding.avatar.drawable as BitmapDrawable).bitmap
                        val drawable = RoundedBitmapDrawableFactory.create(
                            context.resources,
                            source
                        )
                        drawable.cornerRadius =
                            (source.width / 2.0f).coerceAtLeast(source.height / 2.0f)
                        drawable.isCircular = true
                        viewHolder.itemUserBinding.avatar.setImageDrawable(drawable)
                    }

                    override fun onError(e: java.lang.Exception?) {

                    }
                }
            )

            viewHolder.itemUserBinding.name.text = user.login

            viewHolder.itemView.setOnClickListener {
                onUserClick.invoke(user, position)
            }
        }
    }

    class UserViewHolder(val itemUserBinding: ItemUserBinding) : RecyclerView.ViewHolder(itemUserBinding.root)

    companion object {
        private val UserComparator = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }
        }
    }
}