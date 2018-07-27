package com.inspiroes.attendance.Event

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import com.inspiroes.attendance.R
import com.inspiroes.attendance.dashboard.DashboardActivity
import kotlinx.android.synthetic.main.event_row.view.*
import org.jetbrains.anko.intentFor

class EventAdapter(val events: ArrayList<Event>) : RecyclerView.Adapter<EventAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.event_row, parent, false)
        return ViewHolder(layout)
    }

    override fun getItemCount(): Int {
        return events.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(events.get(position))
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        fun bind(event: Event) {
            view.name.text = event.name
            view.venue.text = event.venue
            view.date.text = event.date

            view.row.setOnClickListener{
                view.context.startActivity(view.context.intentFor<DashboardActivity>(
                        "name" to event.name,
                        "days" to event.days,
                        "date" to event.date,
                        "venue" to event.venue

                ))
                Log.d("TESTLABEL","working")
            }
        }
    }
}