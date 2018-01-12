package com.recycler;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alldemo.App;
import com.alldemo.R;
import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;
import com.recycler.newsapi.ArticlesModel;
import com.recycler.newsapi.NewsHeadlinesResponse;
import com.utils.AppFlags;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static java.security.AccessController.getContext;

public class ActNewsListing extends AppCompatActivity {

    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    
    @BindView(R.id.materialRefreshLayout)
    MaterialRefreshLayout materialRefreshLayout;



    @BindView(R.id.llNodata)
    LinearLayout llNodata;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.tvNodata)
    TextView tvNodata;

    DataListAdapter dataListAdapter;
    ArrayList<ArticlesModel> arrayListArticlesModel = new ArrayList<>();


    String strFrom="",strData="",category_id="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_news_listing);
        ButterKnife.bind(this);
        getIntentData();
        initialization();
        asyncGetNewsList();
    }


    private void getIntentData() {
        Bundle bundle;
        if (getIntent() != null && getIntent().getExtras() != null) {
            bundle = getIntent().getExtras();
            if (bundle.getString(AppFlags.tagFrom) != null) {
                strFrom = bundle.getString(AppFlags.tagFrom);
            }


            if (bundle.getString(AppFlags.tagData) != null) {
                strData = bundle.getString(AppFlags.tagData);
            }

            if (bundle.getString(AppFlags.tagCatId) != null) {
                category_id = bundle.getString(AppFlags.tagCatId);
            }
        }

        App.showLog("====strFrom===" + strFrom);
        App.showLog("===strData====" + strData);
        App.showLog("===category_id====" + category_id);

    }

    private void initialization() {
        try {




            materialRefreshLayout.setIsOverLay(true);
            materialRefreshLayout.setWaveShow(true);
            materialRefreshLayout.setWaveColor(0x55ffffff);
            materialRefreshLayout.setLoadMore(false);

            materialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
                @Override
                public void onRefresh(final MaterialRefreshLayout materialRefreshLayout) {
                    try {
                        arrayListArticlesModel = new ArrayList<>();
                        asyncGetNewsList();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                    try {
                        materialRefreshLayout.setLoadMore(false);
                        App.setStopLoadingMaterialRefreshLayout(materialRefreshLayout);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });


            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            //GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
            recyclerView.setLayoutManager(linearLayoutManager);
            //recyclerView.setHasFixedSize(true);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private void asyncGetNewsList() {
        try {
            Ion.with(this)
                    .load("https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=462f5f3ede2841408e9ef575919befe5")
                    .uploadProgressBar(progressBar)


                 /*   .setMultipartParameter("country", "us")
                    .setMultipartParameter("category", "business")
                    .setMultipartParameter("apiKey", "462f5f3ede2841408e9ef575919befe5")
                    */

                   // .setMultipartFile("archive", "application/zip", new File("/sdcard/filename.zip"))
                    .asJsonObject()
                    .setCallback(new FutureCallback<JsonObject>() {
                        @Override
                        public void onCompleted(Exception e, JsonObject result) {
                            // do stuff with the result or error

                            try {
                                progressBar.setVisibility(View.GONE);
                                if (result != null) {
                                    App.showLog("==result==" + result.toString());

                                    Gson gson = new GsonBuilder().create();
                                    NewsHeadlinesResponse newsHeadlinesResponse = gson.fromJson(result.toString(), NewsHeadlinesResponse.class);
                                    App.setStopLoadingMaterialRefreshLayout(materialRefreshLayout);
                                    if (newsHeadlinesResponse != null && newsHeadlinesResponse.arrayListArticlesModel != null) {
                                        arrayListArticlesModel = newsHeadlinesResponse.arrayListArticlesModel;
                                        setStaticData();
                                    }
                                }

                            }catch (Exception e1)
                            {
                                progressBar.setVisibility(View.GONE);
                                App.setStopLoadingMaterialRefreshLayout(materialRefreshLayout);
                                e1.printStackTrace();
                            }

                        }
                    });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void setStaticData() {
        try {

            App.showLog("=======setStaticData===");
            //111 arrayListArticlesModel = categoryArticleListResponse.arrayListPEArticleModel;
            //arrayListArticlesModel = StaticDataList.getPEArticleList2();

           /* if(categoryArticleListResponse !=null && categoryArticleListResponse.arrayListPEArticleModel!=null) {
                arrayListArticlesModel.addAll(categoryArticleListResponse.arrayListPEArticleModel);
            }*/
            if (arrayListArticlesModel != null && arrayListArticlesModel.size() > 0) {

                llNodata.setVisibility(View.GONE);
                App.showLog("======set adapter=DataListAdapter===");

                if(dataListAdapter == null)
                {
                    dataListAdapter = new DataListAdapter(ActNewsListing.this, arrayListArticlesModel);
                    recyclerView.setAdapter(dataListAdapter);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                }
                else
                {
                    dataListAdapter.notifyDataSetChanged();
                }


            } else {
                llNodata.setVisibility(View.VISIBLE);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public class DataListAdapter extends RecyclerView.Adapter<DataListAdapter.VersionViewHolder> {
        ArrayList<ArticlesModel> mArrListmPEArticleModel;
        Context mContext;


        public DataListAdapter(Context context, ArrayList<ArticlesModel> arrList) {
            mArrListmPEArticleModel = arrList;
            mContext = context;
        }

        @Override
        public VersionViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.row_articles_list, viewGroup, false);
            VersionViewHolder viewHolder = new VersionViewHolder(view);
            return viewHolder;
        }


        @Override
        public void onBindViewHolder(final VersionViewHolder versionViewHolder, final int i) {
            try {
                ArticlesModel mPEArticleModel = mArrListmPEArticleModel.get(i);

                versionViewHolder.tvTitle.setText(mPEArticleModel.title);
                versionViewHolder.tvDate.setText(mPEArticleModel.publishedAt);
                versionViewHolder.tvTime.setText(mPEArticleModel.author);

                versionViewHolder.tvDetail.setText(mPEArticleModel.description);

                if (mPEArticleModel.urlToImage != null && mPEArticleModel.urlToImage.length() > 1) {
                    versionViewHolder.ivPhoto.setVisibility(View.VISIBLE);

                    // This is the "long" way to do build an ImageView request... it allows you to set headers, etc.
                    Ion.with(mContext)
                            .load(mPEArticleModel.urlToImage)
                            .withBitmap()
                            .placeholder(R.drawable.ic_menu_gallery)
                            .error(R.drawable.ic_menu_camera)
                            .intoImageView(versionViewHolder.ivPhoto);

                    //Picasso.with(mContext).load(mPEArticleModel.image).placeholder(R.drawable.placeholder_big).fit().centerCrop().into(versionViewHolder.ivPhoto);
                } else {
                    versionViewHolder.ivPhoto.setVisibility(View.GONE);
                }
                if (mPEArticleModel.title.equalsIgnoreCase("1")) {
                    versionViewHolder.ivFavourite.setSelected(true);
                } else {
                    versionViewHolder.ivFavourite.setSelected(false);
                }

                versionViewHolder.cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {

                           /* if (mArrListmPEArticleModel.get(i) != null && mArrListmPEArticleModel.get(i).id != null) {
                                Intent intent = new Intent(ActArticlesList.this, ActArticleDetail.class);

                                intent.putExtra(AppFlags.tagFrom, "ActArticlesList");
                                intent.putExtra(AppFlags.tagSelectedTabPos, intSelectedTabPos);
                                intent.putExtra(AppFlags.tagPEArticleModel, mArrListmPEArticleModel.get(i));

                                if (mArrListmPEArticleModel.get(i).title != null) {
                                    intent.putExtra(AppFlags.tagTitle, mArrListmPEArticleModel.get(i).title);
                                }

                                App.myStartActivity(ActArticlesList.this, intent);

                            } else {
                                App.showLog(TAG + "===Null=====");
                            }*/


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });

                versionViewHolder.ivFavourite.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (mArrListmPEArticleModel.get(i).title.equalsIgnoreCase("1")) {
                            mArrListmPEArticleModel.get(i).title = "0";
                            if (mArrListmPEArticleModel.get(i) != null && mArrListmPEArticleModel.get(i).title != null) {

                               /* if (asyncRemovedSavedContent(mArrListmPEArticleModel.get(i).id, AppFlags.tagContentType_articles) == true) {

                                }*/
                            }
                        } else {
                            mArrListmPEArticleModel.get(i).title = "1";
                          /*  if (asyncSetSavedContent(mArrListmPEArticleModel.get(i).id, AppFlags.tagContentType_articles) == true) {

                            }*/
                        }

                        dataListAdapter.notifyDataSetChanged();
                    }
                });


            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public int getItemCount() {
            return mArrListmPEArticleModel.size();
        }


        public void removeItem(int position) {

            if (App.isInternetAvail(ActNewsListing.this)) {
                if (mArrListmPEArticleModel.get(position).title != null) {
                    // 1- App.OP_NOTI_READ
                    // 2- App.OP_NOTI_DELETE
                    // asyncReadDeleteNotification(App.OP_NOTI_DELETE, mArrListmPEArticleModel.get(position).addr_id);
                }
                mArrListmPEArticleModel.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, mArrListmPEArticleModel.size());

            } else {
                App.showSnackBar(tvNodata, getString(R.string.strNetError));
            }


        }


        class VersionViewHolder extends RecyclerView.ViewHolder {

            CardView cardView;

            TextView tvTitle, tvDate, tvTime, tvDetail;
            ImageView ivPhoto, ivFavourite;


            public VersionViewHolder(View itemView) {
                super(itemView);

                cardView = (CardView) itemView.findViewById(R.id.cardView);

                tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
                tvDate = (TextView) itemView.findViewById(R.id.tvDate);
                tvTime = (TextView) itemView.findViewById(R.id.tvTime);
                tvDetail = (TextView) itemView.findViewById(R.id.tvDetail);

                ivPhoto = (ImageView) itemView.findViewById(R.id.ivPhoto);
                ivFavourite = (ImageView) itemView.findViewById(R.id.ivFavourite);
            }

        }
    }


}
