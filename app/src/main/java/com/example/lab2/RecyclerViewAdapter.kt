package com.example.lab2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.Locale

sealed class ListItem {
    data class DateItem(val date: String) : ListItem()
    data class ActivityItem(val activity: Activity) : ListItem()
}

class RecyclerViewAdapter(
    private var activities: List<Activity>,
    private val fragmentType: FragmentType
) : RecyclerView.Adapter<RecyclerViewAdapter.BaseViewHolder>() {

    private var items: List<ListItem> = listOf()

    var onClick: ( (Int) -> Unit)? = null

    enum class FragmentType {
        MY, USERS
    }

    fun updateData(newActivities: List<Activity>) {
        val sortedActivities = newActivities.sortedByDescending { it.startTime }
        val newList = mutableListOf<ListItem>()

        var lastDate: String? = null
        for (activity in sortedActivities) {
            val currentDate = formatDate(activity.startTime)
            if (currentDate != lastDate) {
                newList.add(ListItem.DateItem(currentDate))
                lastDate = currentDate
            }
            newList.add(ListItem.ActivityItem(activity))
        }

        items = newList
        notifyDataSetChanged()
    }

    fun getItem(position: Int): Activity {
        val item = items[position]
        if (item is ListItem.ActivityItem) {
            return item.activity
        } else {
            throw IllegalArgumentException("Item at position $position is not an ActivityItem")
        }
    }

    private fun formatDate(timestamp: Long): String {
        val sdf = SimpleDateFormat("d MMMM yyyy", Locale("ru"))
        return sdf.format(java.util.Date(timestamp))
    }

    abstract class BaseViewHolder(view: View) : RecyclerView.ViewHolder(view)

    class ActivityItemViewHolder(view: View) : BaseViewHolder(view) {
        val length: TextView = view.findViewById(R.id.length)
        val time: TextView = view.findViewById(R.id.time)
        val activityName: TextView = view.findViewById(R.id.activity_name)
        val whenWas: TextView = view.findViewById(R.id.when_was)

        fun bind(activity: Activity) {
            val durationMillis = activity.endTime - activity.startTime
            val durationMinutes = durationMillis / 1000 / 60

            length.text = "10 м"
            time.text = "$durationMinutes мин"
            activityName.text = activity.type.displayName

            val sdf = SimpleDateFormat("d MMMM, HH:mm", Locale("ru"))
            val formattedDate = sdf.format(java.util.Date(activity.startTime))
            whenWas.text = formattedDate
        }
    }

    class ActivityItemUsersViewHolder(view: View) : BaseViewHolder(view) {
        val length: TextView = view.findViewById(R.id.length)
        val time: TextView = view.findViewById(R.id.time)
        val activityName: TextView = view.findViewById(R.id.activity_name)
        val whenWas: TextView = view.findViewById(R.id.when_was)
        val nikcname: TextView = view.findViewById(R.id.nickname)

        fun bind(activity: Activity) {
            val durationMillis = activity.endTime - activity.startTime
            val durationMinutes = durationMillis / 1000 / 60

            length.text = "10 м"
            time.text = "$durationMinutes мин"
            activityName.text = activity.type.displayName

            val sdf = SimpleDateFormat("d MMMM, HH:mm", Locale("ru"))
            val formattedDate = sdf.format(java.util.Date(activity.startTime))
            whenWas.text = formattedDate
            nikcname.text = "@" + activity.user.toString()
        }
    }

    class ActivityDateViewHolder(view: View) : BaseViewHolder(view) {
        val date: TextView = view.findViewById(R.id.date)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return when(viewType){
            VIEW_TYPE_DATE -> {
                ActivityDateViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.activity_date, parent, false))
            }
            VIEW_TYPE_ACTIVITY -> {
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
        when (val item = items[position]) {
            is ListItem.DateItem -> {
                (holder as ActivityDateViewHolder).date.text = item.date
            }
            is ListItem.ActivityItem -> {
                when (holder) {
                    is ActivityItemViewHolder -> holder.bind(item.activity)
                    is ActivityItemUsersViewHolder -> holder.bind(item.activity)
                }
                holder.itemView.setOnClickListener {
                    onClick?.invoke(position)
                }
            }
        }
    }

    override fun getItemCount(): Int = items.size

    override fun getItemViewType(position: Int): Int {
        return when (items[position]) {
            is ListItem.DateItem -> VIEW_TYPE_DATE
            is ListItem.ActivityItem -> VIEW_TYPE_ACTIVITY
        }
    }

    companion object{
        const val VIEW_TYPE_DATE = 0
        const val VIEW_TYPE_ACTIVITY = 1
    }
}