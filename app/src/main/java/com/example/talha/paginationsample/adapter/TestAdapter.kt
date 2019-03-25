//package com.example.talha.paginationsample.adapter
//
//import android.arch.lifecycle.ViewModelProviders
//import android.arch.paging.PagedList
//import android.arch.paging.PagedListAdapter
//import android.content.Context
//import android.content.Intent
//import android.graphics.Typeface
//import android.os.Bundle
//import android.os.Parcelable
//import android.support.annotation.NonNull
//import android.support.v4.app.FragmentActivity
//import android.support.v7.util.DiffUtil
//import android.support.v7.widget.CardView
//import android.support.v7.widget.RecyclerView
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.paging.PagedListAdapter
//import com.example.mohammed.quicknews.R
//import com.example.mohammed.quicknews.activity.DetailNewsActivity
//import com.example.mohammed.quicknews.db.NewsEntity
//import com.example.mohammed.quicknews.model.ArticlesData
//import com.example.mohammed.quicknews.model.DetailNewsData
//import com.example.mohammed.quicknews.viewmodel.FetchDataApiViewModel
//import com.github.marlonlom.utilities.timeago.TimeAgo
//import com.github.marlonlom.utilities.timeago.TimeAgoMessages
//import com.squareup.picasso.Picasso
//import java.text.SimpleDateFormat
//import java.util.*
//import kotlin.collections.ArrayList
//
//
//class TestAdapter(context: Context,category:String): PagedListAdapte<NewsEntity, NewsAdapter.MainFragmentViewHolder>(DIFF_CALLBACK) {//NewsEntity
//
//    var con:Context = context
//    var itemIndex:Int?=null
//    var list = ArrayList<NewsEntity>()
//    var detailArticleList = ArrayList<DetailNewsData>()
//    var categoryType = category
//    var fetchDataViewModel: FetchDataApiViewModel
//    var widthInPixel:Int=0
//    var heightInPixel:Int=0
//    var dateString:String? = null
//
//    companion object {
//        var TAG:String ="NewsAdapter"
//
////         val DIFF_CALLBACK:DiffUtil.ItemCallback<NewsEntity> = object:DiffUtil.ItemCallback<NewsEntity>() {
////
////             override fun areItemsTheSame(oldData:NewsEntity,newData:NewsEntity):Boolean {
////                 // User properties may have changed if reloaded from the DB, but ID is fixed
////                 return oldData.id == newData.id
////             }
////
////             override fun areContentsTheSame(oldData:NewsEntity,newData:NewsEntity):Boolean {
////                 // NOTE: if you use equals, your object must properly override Object#equals()
////                 // Incorrectly returning false here will result in too many animations.
////                 return oldData.equals(newData)
////             }
////         }
//
//        val DIFF_CALLBACK:DiffUtil.ItemCallback<NewsEntity> = object:DiffUtil.ItemCallback<NewsEntity>() {
//
//            override fun areItemsTheSame(oldData:NewsEntity,newData:NewsEntity):Boolean {
//                // User properties may have changed if reloaded from the DB, but ID is fixed
//                return oldData.id == newData.id
//            }
//
//            override fun areContentsTheSame(oldData:NewsEntity,newData:NewsEntity):Boolean {
//                // NOTE: if you use equals, your object must properly override Object#equals()
//                // Incorrectly returning false here will result in too many animations.
//                return oldData.equals(newData)
//            }
//        }
//    }
//
//
//
//    init {
//        fetchDataViewModel = ViewModelProviders.of(context as FragmentActivity).get(FetchDataApiViewModel::class.java)
//        widthInPixel = convertDpToPx(context,60)
//        heightInPixel = convertDpToPx(context,60)
//    }
//
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainFragmentViewHolder {
//
//        var view = LayoutInflater.from(parent.context)
//                .inflate(R.layout.news_item_main,parent,false)
//        var list = currentList
//        // Log.d("news_adapter_list",list.toString())
//        return MainFragmentViewHolder(view)
//    }
//
////    override fun getItemCount(): Int {
//////        return  this.list.size
////        if (detailArticleList ==null){
////            return 0
////        }
////
////        return  this.detailArticleList.size
////    }
//
//    override fun onBindViewHolder(holder: MainFragmentViewHolder, position: Int) {
//
//        getItem(position).let {
//            Log.d("list_size",it?.title.toString() +" "+it?.id)
//            holder.newsSource.text = it?.source
//            holder.newsSource.typeface = Typeface.createFromAsset(con.assets,"HelveticaNeueLight.ttf")
//            holder.newsTitle.text = it?.title
//            holder.newsTitle.typeface = Typeface.createFromAsset(con.assets,"HelveticaNeueBold.ttf")
//            if (it?.cover_image!=null && it?.cover_image?.length!! >0){
//                Picasso.with(con).load(it.cover_image).resize(widthInPixel,heightInPixel).centerCrop().placeholder(R.drawable.image_not_found).into(holder.newsImage)
//            }else{
//                Picasso.with(con).load(R.drawable.image_not_found).into(holder.newsImage)
//            }
//            if (it?.published_on!=null){
//                dateString = it!!.published_on
//                Log.d(TAG+"dateString",dateString)
//
//                if (dateString?.endsWith("Z",false)==false){
//                    dateString+="Z"
//                }
//                var timeZone = Calendar.getInstance().timeZone.id
//                var dateformat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
//                dateformat.timeZone = TimeZone.getTimeZone("UTC")
//                var date =dateformat.parse(dateString)
//                dateformat.timeZone = TimeZone.getTimeZone(timeZone)
//                dateformat.format(date)
//
//                var timeAgo:String = TimeAgo.using(date.time,TimeAgoMessages.Builder().defaultLocale().build())
//
//                holder.newsTime.text =  timeAgo
//                holder.newsTime.typeface = Typeface.createFromAsset(con.assets,"HelveticaNeueLight.ttf")
//
//            }else{
//                holder.newsTime.text = ""
//            }
//
//
//        }
////        holder.newsSource.text = detailArticleList.get(position).source
////        holder.newsSource.typeface = Typeface.createFromAsset(con.assets,"HelveticaNeueLight.ttf")
////        holder.newsTitle.text = detailArticleList.get(position).title
////        holder.newsTitle.typeface = Typeface.createFromAsset(con.assets,"HelveticaNeueBold.ttf")
////        if (detailArticleList.get(position).cover_image.length>0){
////            Picasso.with(con).load(detailArticleList.get(position).cover_image).resize(widthInPixel,heightInPixel).placeholder(R.drawable.image_not_found).into(holder.newsImage)
////        }else{
////            Picasso.with(con).load(R.drawable.image_not_found).into(holder.newsImage)
////        }
////
////        var dateString:String = detailArticleList.get(position).published_on
////        Log.d(TAG+"dateString",dateString)
////
////        var timeZone = Calendar.getInstance().timeZone.id
////        var dateformat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
////        dateformat.timeZone = TimeZone.getTimeZone("UTC")
////        var date =dateformat.parse(dateString)
////        dateformat.timeZone = TimeZone.getTimeZone(timeZone)
////        dateformat.format(date)
////
////        var timeAgo:String = TimeAgo.using(date.time,TimeAgoMessages.Builder().defaultLocale().build())
////
////        holder.newsTime.text =  timeAgo
////        holder.newsTime.typeface = Typeface.createFromAsset(con.assets,"HelveticaNeueLight.ttf")
//
//
//        holder.itemCardView.setOnClickListener{
//            itemIndex = position
//            // var id = detailArticleList.get(position).article_id
//            var id = it.id
//            // fetchDataViewModel.startRecommendNewsWorkManager(id)
//            var detailIntent = Intent(con,DetailNewsActivity::class.java)
//            detailIntent.putExtra("indexPosition", itemIndex!!)
//            // detailIntent.putParcelableArrayListExtra("arrayList",detailArticleList)
//            detailIntent.putExtra("category_of_newslist",categoryType)
//            con.startActivity(detailIntent)
//        }
//
//    }
//
////    fun setArticleData(result:ArrayList<NewsEntity>?){
////        if (result==null){
////
////        }else{
////            this.list.clear()
////            this.list.addAll(result)
////            notifyDataSetChanged()
////        }
////
////    }
//
//    fun setArticleDetailData(result:ArrayList<DetailNewsData>?){
//        if (result==null){
//
//        }else{
//            this.detailArticleList.clear()
//            this.detailArticleList.addAll(result)
//            notifyDataSetChanged()
//        }
//
//    }
//
//
//
//    class MainFragmentViewHolder(view:View):RecyclerView.ViewHolder(view){
//        val itemCardView = view.findViewById<CardView>(R.id.cv_news_item)
//        var newsSource = view.findViewById<TextView>(R.id.news_source_main)
//        var newsTitle = view.findViewById<TextView>(R.id.news_title_main)
//        var newsImage = view.findViewById<ImageView>(R.id.news_image_main)
//        var newsTime = view.findViewById<TextView>(R.id.news_time_main)
//    }
//
//    fun convertDpToPx(context: Context,dp:Int):Int{
//        return dp* context.resources.displayMetrics.density.toInt()
//    }
//
//
//
//
//}
//
