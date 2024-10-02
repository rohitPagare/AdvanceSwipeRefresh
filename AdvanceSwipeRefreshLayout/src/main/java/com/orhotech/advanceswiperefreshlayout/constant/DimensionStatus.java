package com.orhotech.advanceswiperefreshlayout.constant;

/**
 * The definition status of the size value, used to determine the priority when the value is overwritten
 * The further down, the higher the priority.
 */
@SuppressWarnings("WeakerAccess")
public class DimensionStatus {

    public static final DimensionStatus DefaultUnNotify = new DimensionStatus(0,false);//默认值，但是还没通知确认
    public static final DimensionStatus Default = new DimensionStatus(1,true);//默认值
    public static final DimensionStatus XmlWrapUnNotify = new DimensionStatus(2,false);//Xml计算，但是还没通知确认
    public static final DimensionStatus XmlWrap = new DimensionStatus(3,true);//Xml计算
    public static final DimensionStatus XmlExactUnNotify = new DimensionStatus(4,false);//Xml 的view 指定，但是还没通知确认
    public static final DimensionStatus XmlExact = new DimensionStatus(5,true);//Xml 的view 指定
    public static final DimensionStatus XmlLayoutUnNotify = new DimensionStatus(6,false);//Xml 的layout 中指定，但是还没通知确认
    public static final DimensionStatus XmlLayout = new DimensionStatus(7,true);//Xml 的layout 中指定
    public static final DimensionStatus CodeExactUnNotify = new DimensionStatus(8,false);//代码指定，但是还没通知确认
    public static final DimensionStatus CodeExact = new DimensionStatus(9,true);//代码指定
    public static final DimensionStatus DeadLockUnNotify = new DimensionStatus(10,false);//锁死，但是还没通知确认
    public static final DimensionStatus DeadLock = new DimensionStatus(10,true);//锁死

    public final int ordinal;
    public final boolean notified;

    public static final DimensionStatus[] values = new DimensionStatus[]{
            DefaultUnNotify,
            Default,
            XmlWrapUnNotify,
            XmlWrap,
            XmlExactUnNotify,
            XmlExact,
            XmlLayoutUnNotify,
            XmlLayout,
            CodeExactUnNotify,
            CodeExact,
            DeadLockUnNotify,
            DeadLock
    };

    private DimensionStatus(int ordinal,boolean notified) {
        this.ordinal = ordinal;
        this.notified = notified;
    }

    /**
     * Convert to unnotified state
     * @return unnotified status
     */
    public DimensionStatus unNotify() {
        if (notified) {
            DimensionStatus prev = values[ordinal - 1];
            if (!prev.notified) {
                return prev;
            }
            return DefaultUnNotify;
        }
        return this;
    }

    /**
     * Convert to notification state
     * @return notification status
     */
    public DimensionStatus notified() {
        if (!notified) {
            return values[ordinal + 1];
        }
        return this;
    }

    /**
     * Whether it can be replaced by a new state
     * @param status new turntable
     * @return less than or equal to
     */
    public boolean canReplaceWith(DimensionStatus status) {
        return ordinal < status.ordinal || ((!notified || CodeExact == this) && ordinal == status.ordinal);
    }

//    /**
// * Whether the new state has not been reached
// * @param status new turntable
// * @return greater than or equal to gte
// */
// public boolean gteStatusWith(DimensionStatus status) {
// return ordinal() >= status.ordinal();
// }
}