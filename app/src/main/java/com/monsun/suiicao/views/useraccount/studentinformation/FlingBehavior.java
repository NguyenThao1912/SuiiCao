package com.monsun.suiicao.views.useraccount.studentinformation;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.AppBarLayout;

public final class 	FlingBehavior extends AppBarLayout.Behavior {

	private static final int TOP_CHILD_FLING_THRESHOLD = 3;
	private boolean isPositive;

	public FlingBehavior() {
		RecyclerView a ;
	}

	public FlingBehavior(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	@Override
	public boolean onNestedFling(@NonNull CoordinatorLayout coordinatorLayout,
                                 @NonNull AppBarLayout child, @NonNull View target, float velocityX,
                                 float velocityY, boolean consumed) {
		if (velocityY > 0 && !isPositive || velocityY < 0 && isPositive) {
			velocityY = velocityY * -1;
		}
		if (target instanceof RecyclerView && velocityY < 0) {
			final RecyclerView recyclerView = (RecyclerView) target;
			final View firstChild = recyclerView.getChildAt(0);
			final int childAdapterPosition = recyclerView.getChildAdapterPosition(firstChild);
			consumed = childAdapterPosition > TOP_CHILD_FLING_THRESHOLD;
		}
		return super
				.onNestedFling(coordinatorLayout, child, target, velocityX, velocityY, consumed);
	}

	@Override
	public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, AppBarLayout child,
                                  View target, int dx, int dy, int[] consumed, int type) {
		super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);
		isPositive = dy > 0;
	}
}