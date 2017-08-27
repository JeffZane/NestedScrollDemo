package com.demo.yzg.nestedscroll;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class TestFragment extends Fragment {

    private static final String BUNDLE_KEY_PAGE_INDEX = "BUNDLE_KEY_PAGE_INDEX";

    private int mIndex;

    public static TestFragment newInstance(int index) {
        Bundle args = new Bundle();
        args.putInt(BUNDLE_KEY_PAGE_INDEX, index);
        TestFragment fragment = new TestFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle arguments = getArguments();
        if (arguments != null && arguments.containsKey(BUNDLE_KEY_PAGE_INDEX)) {
            mIndex = (int) arguments.get(BUNDLE_KEY_PAGE_INDEX);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test, container, false);
        setupRecyclerView(view);
        return view;
    }

    private void setupRecyclerView(View view) {
        final SwipeRefreshLayout swipeRefreshLayout = view.findViewById(R.id.srl);
        swipeRefreshLayout.setColorSchemeColors(Color.parseColor("#419bf9"));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 1200);
            }
        });

        RecyclerView mRecyclerView = view.findViewById(R.id.rv);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(new MyAdapter());
    }

    private class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_news_item, parent, false);
            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.setData((mIndex == 0 ? "Top Reads " : "Video News ") + position);
        }

        @Override
        public int getItemCount() {
            return 30;
        }
    }

    private class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvNews;

        MyViewHolder(View itemView) {
            super(itemView);
            tvNews = itemView.findViewById(R.id.tv_news);
        }

        void setData(String news) {
            tvNews.setText(news);
//            tvNews.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Toast.makeText(view.getContext(), tvNews.getText(), Toast.LENGTH_SHORT).show();
//                }
//            });
        }
    }
}
