package com.example.talha.paginationsample.adapter
import android.arch.paging.PagedListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.talha.paginationsample.R
import com.example.talha.paginationsample.db.RepoEntity




class RecyclerViewAdapter(): PagedListAdapter<RepoEntity, RecyclerViewAdapter.MyViewHolder> (DIFF_CALLBACK){

        companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<RepoEntity> = object: DiffUtil.ItemCallback<RepoEntity>()
        {

            override fun areItemsTheSame(oldData: RepoEntity, newData: RepoEntity): Boolean {
                // User properties may have changed if reloaded from the DB, but ID is fixed
                return oldData.id == newData.id
            }

            override fun areContentsTheSame(oldData: RepoEntity, newData: RepoEntity): Boolean {
                // NOTE: if you use equals, your object must properly override Object#equals()
                // Incorrectly returning false here will result in too many animations.
                return oldData.equals(newData)
            }
        }

    }

//    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder {
//        return MyViewHolder(parent!!.rootView)
//    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): MyViewHolder {
        var view = LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_item,parent,false)
        return MyViewHolder(view)
    }


//
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
         //To change body of created functions use File | Settings | File Templates.
        val repoItem = getItem(position)
        holder.title.text = repoItem!!.name
        holder.description.text = repoItem.description
        holder.description.text = repoItem.full_name
    }



    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var title = itemView.findViewById<TextView>(R.id.repo_title)
        var description = itemView.findViewById<TextView>(R.id.repo_description)
        var repoFullName = itemView.findViewById<TextView>(R.id.repo_full_name)
    }

}