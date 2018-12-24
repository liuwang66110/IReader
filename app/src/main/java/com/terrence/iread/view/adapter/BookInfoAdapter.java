package com.terrence.iread.view.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.terrence.iread.R;
import com.terrence.iread.model.BookBean;
import com.terrence.iread.utils.BaseUtils;
import com.terrence.iread.utils.Constant;

import java.util.List;


public class BookInfoAdapter extends BaseQuickAdapter<BookBean, BaseViewHolder> {
    public BookInfoAdapter(@Nullable List<BookBean> data) {
        super(R.layout.item_bookinfo, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BookBean item) {
//        int follower = item.getLatelyFollower() / 10000;
        String wordCount = item.getLatelyFollower() / 10000 > 0 ? BaseUtils.format1Digits((double)item.getLatelyFollower() / 10000 ) + "万" : item.getLatelyFollower() + "";

        helper.setText(R.id.book_brief_tv_title, item.getTitle())
                .setText(R.id.book_brief_tv_author, item.getAuthor() + "  |  " + item.getMajorCate())
                .setText(R.id.book_brief_tv_brief, item.getLongIntro())
                .setText(R.id.ctv_arrow_count, wordCount + "")
                .setText(R.id.ctv_retention, item.getRetentionRatio() + "%");

        Glide.with(mContext).load(Constant.ZHUISHU_IMAGE_URL + item.getCover())
                .apply(new RequestOptions().placeholder(R.mipmap.ic_book_loading))/*.transform(new GlideRoundTransform(mContext))*/
                .into((ImageView) helper.getView(R.id.book_brief_iv_portrait));

    }
}
