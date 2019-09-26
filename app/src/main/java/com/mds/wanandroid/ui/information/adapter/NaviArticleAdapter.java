package com.mds.wanandroid.ui.information.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mds.wanandroid.R;
import com.mds.wanandroid.ui.information.bean.NavigateBean;
import com.mds.wanandroid.utils.Constant;

import java.util.List;

/**
 * @author duanjianlin
 * @description: 导航页Adapter
 * @date : 19/09/26 15:33
 */
public class NaviArticleAdapter extends RecyclerView.Adapter<NaviArticleAdapter.VH> {

    private Context mContext;
    private List<NavigateBean.DataBean.ArticlesBean> mArticleList;
    private OnItemClickListener mOnItemClickListener;

    public NaviArticleAdapter(Context context, List<NavigateBean.DataBean.ArticlesBean> data){
        mContext = context;
        mArticleList = data;
    }

    public void setmOnItemClickListener(OnItemClickListener listener){
        mOnItemClickListener = listener;
    }
    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View root;
        if(viewType== Constant.ARTICLE_CATEGORY){
            root = LayoutInflater.from(mContext).inflate(R.layout.recyclerview_item_right_big_sort,viewGroup,false);
        }else{
            root = LayoutInflater.from(mContext).inflate(R.layout.recyclerview_item_right_small_sort,viewGroup,false);
        }
        return new VH(root,viewType);
    }

    @Override
    public void onBindViewHolder(@NonNull VH vh, int position) {
        NavigateBean.DataBean.ArticlesBean articlesBean = mArticleList.get(position);
        TextView nameTV = vh.getNameTV();
        if(articlesBean.viewType==Constant.ARTICLE_CATEGORY){
            nameTV.setText(mArticleList.get(position).header);
        }else {
            nameTV.setText(mArticleList.get(position).getTitle());
            nameTV.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mOnItemClickListener!=null){
                        mOnItemClickListener.onArticleItemClick(mArticleList.get(position));
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mArticleList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return mArticleList.get(position).viewType;
    }

    static class VH extends RecyclerView.ViewHolder{

        private TextView nameTV;
        public VH(@NonNull View itemView,int viewType) {
            super(itemView);
            if(viewType== Constant.ARTICLE_CATEGORY){
                nameTV = itemView.findViewById(R.id.tv_title);
            }else{
                nameTV = itemView.findViewById(R.id.tv_small);
            }
        }
        public TextView getNameTV(){
            return nameTV;
        }
    }
    public interface OnItemClickListener{
        void onArticleItemClick(NavigateBean.DataBean.ArticlesBean articlesBean);
    }
}
