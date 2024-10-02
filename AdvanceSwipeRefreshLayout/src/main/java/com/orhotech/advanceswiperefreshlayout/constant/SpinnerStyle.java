package com.orhotech.advanceswiperefreshlayout.constant;

/**
 * How the top and bottom components transform when dragged
 * Created by rohit on 2024/10/02.
 */
@SuppressWarnings("DeprecatedIsStillUsed")
public class SpinnerStyle {

    public static final SpinnerStyle Translate = new SpinnerStyle(0, true, false);
    /**
     * During the Scale pull-down process, [Measurement] (header) and [Layout] (layout) will be dynamically reduced to reduce app performance.
     * The official headers have been changed from [Scale] to [FixedBehind] to improve performance
     * For customization, you can refer to the official [Airplane] [Bessel] [Express] and other Headers
     * @deprecated use {@link SpinnerStyle#FixedBehind}
     */
    @Deprecated
    public static final SpinnerStyle Scale = new SpinnerStyle(1, true, true);
    public static final SpinnerStyle FixedBehind = new SpinnerStyle(2, false, false);
    public static final SpinnerStyle FixedFront = new SpinnerStyle(3, true, false);
    public static final SpinnerStyle MatchLayout = new SpinnerStyle(4, true, false);

    public static final SpinnerStyle[] values = new SpinnerStyle[] {
            Translate, //Parallel movement Features: HeaderView height will not change,
            Scale, //Stretch deformation Features: When pulling down and popping up (HeaderView height changes), the OnDraw event will be automatically triggered
            FixedBehind, //Fixed at the back Features: HeaderView height will not change,
            FixedFront, //Fixed in front Features: HeaderView height will not change,
            MatchLayout//Fill the layout Features: HeaderView height will not change, size is filled RefreshLayout
    };

    public final int ordinal;
    public final boolean front;
    public final boolean scale;

    protected SpinnerStyle(int ordinal, boolean front, boolean scale) {
        this.ordinal = ordinal;
        this.front = front;
        this.scale = scale;
    }
}
