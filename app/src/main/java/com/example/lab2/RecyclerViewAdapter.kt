package com.example.lab2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(
    private val activities: List<Activity>,
    private val fragmentType: FragmentType
) : RecyclerView.Adapter<RecyclerViewAdapter.BaseViewHolder>() {

//    private var onClickListener: View.OnClickListener? = null
    var onClick: ( (Int) -> Unit)? = null

    enum class FragmentType {
        MY, USERS
    }

    abstract class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view)

    class ActivityItemViewHolder(view: View) : BaseViewHolder(view) {
        val length: TextView = view.findViewById(R.id.length)
        val time: TextView = view.findViewById(R.id.time)
        val activityName: TextView = view.findViewById(R.id.activity_name)
        val whenWas: TextView = view.findViewById(R.id.when_was)

        fun bind(activity: Activity) {
            length.text = activity.length
            time.text = activity.time
            activityName.text = activity.name
            whenWas.text = activity.whenWas

//            itemView.setOnClickListener { onItemClick(activity) }
        }
    }

    class ActivityItemUsersViewHolder(view: View) : BaseViewHolder(view) {
        val length: TextView = view.findViewById(R.id.length)
        val time: TextView = view.findViewById(R.id.time)
        val activityName: TextView = view.findViewById(R.id.activity_name)
        val whenWas: TextView = view.findViewById(R.id.when_was)
        val nikcname: TextView = view.findViewById(R.id.nickname)

        fun bind(activity: Activity) {
            length.text = activity.length
            time.text = activity.time
            activityName.text = activity.name
            whenWas.text = activity.whenWas
            nikcname.text = activity.user

//            itemView.setOnClickListener { onItemClick(activity) }
        }
    }

    class ActivityDateViewHolder(view: View) : BaseViewHolder(view) {
        val date: TextView = view.findViewById(R.id.date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when(viewType){
            VIEW_TYPE_1 -> {
                ActivityDateViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.activity_date, parent, false))
            }
            VIEW_TYPE_2 -> {
                when (fragmentType){
                    FragmentType.MY -> ActivityItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.activity_item, parent, false))
                    FragmentType.USERS -> ActivityItemUsersViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.activity_item_users, parent, false))
                    else -> ActivityItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.activity_item, parent, false))
                }
            }
            else -> throw IllegalArgumentException("Invalid view type")
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val item = activities[position]
        when(holder){
            is ActivityItemViewHolder -> {
                holder.bind(item)
            }
            is ActivityItemUsersViewHolder -> {
                holder.bind(item)
            }
            is ActivityDateViewHolder -> {
                holder.date.text = "День " + (position / 5 + 1).toString()
            }
        }
        holder.itemView.setOnClickListener {
            onClick?.invoke(position)
        }
    }
//
//    fun setOnClickListener(listener: OnClickListener?) {
//        this.onClickListener = listener
//    }
//
//    interface OnClickListener : View.OnClickListener {
//        fun onClick(position: Int, model: Activity)
//    }

    override fun getItemCount(): Int {
        return activities.size
    }

    override fun getItemViewType(position: Int): Int {
        return if(position  % 5 == 0) VIEW_TYPE_1 else VIEW_TYPE_2
    }

    companion object{
//        fun setOnClickListener(onClickListener: RecyclerViewAdapter.OnClickListener) {
//            fun onClick(position: Int, model: Activity){}
//        }

        const val VIEW_TYPE_1 = 0
        const val VIEW_TYPE_2 = 1
    }
}