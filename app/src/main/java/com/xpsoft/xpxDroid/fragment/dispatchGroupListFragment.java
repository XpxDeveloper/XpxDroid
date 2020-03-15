package com.xpsoft.xpxDroid.fragment;

import androidx.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xpsoft.xpxDroid.R;
import com.xpsoft.xpxDroid.adapter.recyclerview.DividerItemDecoration;
import com.xpsoft.xpxDroid.adapter.recyclerview.dispatchgrouplistAdapter;
import com.xpsoft.xpxDroid.databinding.FragmentGrouplistBinding;
import com.xpsoft.xpxDroid.fragment.lazybase_01.lazyFragment;
import com.xpsoft.xpxDroid.models.eventbus.xpxEvent;
import com.xpsoft.xpxDroid.tools.WidgetIdUtils;
import com.yanzhenjie.recyclerview.swipe.SwipeItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;

/**
 * Created by XPSoft on 2018/2/16.
 */

public class dispatchGroupListFragment extends lazyFragment {
    private FragmentGrouplistBinding mBinding;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mBinding = DataBindingUtil.inflate(mInflater, R.layout.fragment_grouplist, null, false);
        mContainer = mBinding.getRoot();
        mContainer.setId(WidgetIdUtils.generateViewId());

        ArrayList<String> data = new ArrayList<>();
        String temp = " item";
        for (int i = 0; i < 20; i++) {
            data.add(i + temp);
        }
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        dispatchgrouplistAdapter mAdapter = new dispatchgrouplistAdapter(data);
        // 设置布局管理器
        mBinding.SwipeMenuRecyclerView.setLayoutManager(mLayoutManager);

        mBinding.SwipeMenuRecyclerView.addItemDecoration(new DividerItemDecoration(mContext, LinearLayoutManager.VERTICAL));
        mAdapter.setOnItemClickListener(new dispatchgrouplistAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (getParentViewPager() != null) {
                    getParentViewPager().setCurrentItem(1);
                }
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
        mBinding.SwipeMenuRecyclerView.setSwipeItemClickListener(new SwipeItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                // TODO，搞事情...
                if (getParentViewPager() != null) {
                    getParentViewPager().setCurrentItem(1);
                }
            }
        });

        SwipeMenuItemClickListener mMenuItemClickListener = new SwipeMenuItemClickListener() {
            @Override
            public void onItemClick(SwipeMenuBridge menuBridge) {
                // 任何操作必须先关闭菜单，否则可能出现Item菜单打开状态错乱。
                menuBridge.closeMenu();

                int direction = menuBridge.getDirection(); // 左侧还是右侧菜单。
                int adapterPosition = menuBridge.getAdapterPosition(); // RecyclerView的Item的position。
                int menuPosition = menuBridge.getPosition(); // 菜单在RecyclerView的Item中的Position。

                Log.e("SwipeMenuItemClick", "onItemClick: "+direction+"，"+menuPosition);
            }
        };
        // 菜单点击监听。
        mBinding.SwipeMenuRecyclerView.setSwipeMenuItemClickListener(mMenuItemClickListener);

        // 创建菜单：
        SwipeMenuCreator mSwipeMenuCreator = new SwipeMenuCreator() {
            @Override
            public void onCreateMenu(SwipeMenu leftMenu, SwipeMenu rightMenu, int viewType) {
                /*SwipeMenuItem deleteItem = new SwipeMenuItem(mContext); // 各种文字和图标属性设置。
                leftMenu.addMenuItem(deleteItem); // 在Item左侧添加一个菜单。*/
                int width = getResources().getDimensionPixelSize(R.dimen.dp_70);

                // 1. MATCH_PARENT 自适应高度，保持和Item一样高;
                // 2. 指定具体的高，比如80;
                // 3. WRAP_CONTENT，自身高度，不推荐;
                int height = ViewGroup.LayoutParams.MATCH_PARENT;

                SwipeMenuItem closeItem = new SwipeMenuItem(mContext)
//                        .setBackground(R.drawable.btn_back_b)
//                        .setImage(R.mipmap.ic_launcher)
                        .setBackgroundColor(Color.GRAY)
                        .setText("添加")
                        .setTextColor(Color.WHITE)
                        .setWidth(width)
                        .setHeight(height);
                rightMenu.addMenuItem(closeItem); // 添加一个按钮到右侧菜单。

                SwipeMenuItem deleteItem = new SwipeMenuItem(mContext)
//                        .setBackground(R.drawable.btn_back_b)
                        .setBackgroundColor(Color.RED)
//                        .setImage(R.mipmap.ic_launcher)
                        .setText("删除")
                        .setTextColor(Color.WHITE)
                        .setWidth(width)
                        .setHeight(height);
                rightMenu.addMenuItem(deleteItem);// 添加一个按钮到右侧侧菜单。



                // 注意：哪边不想要菜单，那么不要添加即可。
            }
        };

        // 设置监听器。
         mBinding.SwipeMenuRecyclerView.setSwipeMenuCreator(mSwipeMenuCreator);

        mBinding.refreshLayout.setOnRefreshListener(mRefreshListener); // 刷新监听。
        mBinding.SwipeMenuRecyclerView.useDefaultLoadMore(); // 使用默认的加载更多的View。
        mBinding.SwipeMenuRecyclerView.setLoadMoreListener(mLoadMoreListener); // 加载更多的监听。

        // 设置adapter
        mBinding.SwipeMenuRecyclerView.setAdapter(mAdapter);

        return mContainer;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void XpxEvent(xpxEvent _xpxEvent) {

    }

    /**
     * 刷新。
     */
    private SwipeRefreshLayout.OnRefreshListener mRefreshListener = new SwipeRefreshLayout.OnRefreshListener() {
        @Override
        public void onRefresh() {
            mBinding.SwipeMenuRecyclerView.postDelayed(new Runnable() {
                @Override
                public void run() {
//                    loadData();
                    mBinding.refreshLayout.setRefreshing(false);

                    // 第一次加载数据：一定要调用这个方法，否则不会触发加载更多。
                    // 第一个参数：表示此次数据是否为空，假如你请求到的list为空(== null || list.size == 0)，那么这里就要true。
                    // 第二个参数：表示是否还有更多数据，根据服务器返回给你的page等信息判断是否还有更多，这样可以提供性能，如果不能判断则传true。
                    mBinding.SwipeMenuRecyclerView.loadMoreFinish(false, true);
                }
            }, 1000); // 延时模拟请求服务器。
        }
    };
    /**
     * 加载更多。
     */
    private int loadCnt=0;
    private SwipeMenuRecyclerView.LoadMoreListener mLoadMoreListener = new SwipeMenuRecyclerView.LoadMoreListener() {
        @Override
        public void onLoadMore() {
            mBinding.SwipeMenuRecyclerView.postDelayed(new Runnable() {
                @Override
                public void run() {
                   /* List<String> strings = createDataList(mAdapter.getItemCount());
                    mDataList.addAll(strings);
                    // notifyItemRangeInserted()或者notifyDataSetChanged().
                    mAdapter.notifyItemRangeInserted(mDataList.size() - strings.size(), strings.size());*/

                    loadCnt++;
                    // 数据完更多数据，一定要掉用这个方法。
                    // 第一个参数：表示此次数据是否为空。
                    // 第二个参数：表示是否还有更多数据。
                    if(loadCnt<2){
                        mBinding.SwipeMenuRecyclerView.loadMoreFinish(false, true);
                    }else {
                        mBinding.SwipeMenuRecyclerView.loadMoreFinish(true, false);
                    }


                    // 如果加载失败调用下面的方法，传入errorCode和errorMessage。
                    // errorCode随便传，你自定义LoadMoreView时可以根据errorCode判断错误类型。
                    // errorMessage是会显示到loadMoreView上的，用户可以看到。
                    // mRecyclerView.loadMoreError(0, "请求网络失败");
                }
            }, 1000);
        }
    };
}

