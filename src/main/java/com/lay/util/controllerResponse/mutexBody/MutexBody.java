package com.lay.util.controllerResponse.mutexBody;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

/**
 * @Version 1.0
 * @Author lei.yue
 * @Created 2021/2/24 11:46
 * @Description <p>
 * @Modification <p>
 * Date Author Version Description
 * <p>
 * 2021/2/24 lei.yue 1.0 create file
 */
@JsonAutoDetect(fieldVisibility = ANY, getterVisibility = NONE, setterVisibility = NONE, isGetterVisibility = NONE, creatorVisibility = NONE)
public final class MutexBody<P, S> {

    private P primary;

    private S secondary;

    public static <P, S> MutexBody<P, S> empty() {
        return new MutexBody<>();
    }

    public static <P, S> MutexBody<P, S> primary(P primary) {
        return new MutexBody<>(primary, null);
    }

    public static <P, S> MutexBody<P, S> secondary(S secondary) {
        return new MutexBody<>(null, secondary);
    }

    private MutexBody() {
    }

    private MutexBody(P primary, S secondary) {
        this.primary = primary;
        this.secondary = secondary;
    }

    public P getPrimary() {
        return primary;
    }

    public S getSecondary() {
        return secondary;
    }

    public boolean isEmpty() {
        return isNull(primary) && isNull(secondary);
    }

    public Object decide() {
        Object body = nonNull(getPrimary()) ? getPrimary() : getSecondary();
        return body instanceof MutexBody ? ((MutexBody<?, ?>) body).decide() : body;
    }

}
