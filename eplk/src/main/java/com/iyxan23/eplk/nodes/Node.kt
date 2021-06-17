package com.iyxan23.eplk.nodes

import com.iyxan23.eplk.interpreter.RealtimeResult
import com.iyxan23.eplk.interpreter.Scope

abstract class Node() {
    abstract fun visit(scope: Scope): RealtimeResult
}
