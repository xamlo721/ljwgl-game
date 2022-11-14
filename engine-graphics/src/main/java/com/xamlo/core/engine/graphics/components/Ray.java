package com.xamlo.core.engine.graphics.components;

import ru.satomi.dc.primitive.Vec3f;

public class Ray {
	
    public final Vec3f origin = new Vec3f();
    public final Vec3f direction = new Vec3f();

    public Ray(){}

    public Ray (Vec3f origin, Vec3f direction) {
        this.origin.set(origin);
        this.direction.set(direction);
        this.direction.normalize();
    }


}
