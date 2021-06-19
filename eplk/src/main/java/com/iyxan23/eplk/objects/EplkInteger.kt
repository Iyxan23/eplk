package com.iyxan23.eplk.objects

import com.iyxan23.eplk.errors.EplkNotImplementedError
import com.iyxan23.eplk.interpreter.RealtimeResult
import com.iyxan23.eplk.interpreter.Scope
import com.iyxan23.eplk.lexer.models.Position
import kotlin.math.pow

/**
 * An Integer object in EPLK
 */
class EplkInteger(
    val value: Int,
    override val scope: Scope
) : EplkObject(scope) {

    override val objectName = "Integer"

    override fun operatorPlus(
        other: EplkObject,
        startPosition: Position,
        endPosition: Position
    ): RealtimeResult<EplkObject> {

        return when (other) {
            is EplkInteger ->
                RealtimeResult<EplkObject>().success(EplkInteger(value + other.value, scope))

            is EplkFloat ->
                RealtimeResult<EplkObject>().success(EplkFloat(value + other.value, scope))

            else ->
                RealtimeResult<EplkObject>().failure(EplkNotImplementedError(
                    "+ operator with ${other.objectName} is not supported",
                    startPosition,
                    endPosition,
                    scope
                ))
        }
    }

    override fun operatorMinus(
        other: EplkObject,
        startPosition: Position,
        endPosition: Position
    ): RealtimeResult<EplkObject> {

        return when (other) {
            is EplkInteger ->
                RealtimeResult<EplkObject>().success(EplkInteger(value - other.value, scope))

            is EplkFloat ->
                RealtimeResult<EplkObject>().success(EplkFloat(value - other.value, scope))

            else ->
                RealtimeResult<EplkObject>().failure(EplkNotImplementedError(
                    "- operator with ${other.objectName} is not supported",
                    startPosition,
                    endPosition,
                    scope
                ))
        }
    }

    override fun operatorMultiply(
        other: EplkObject,
        startPosition: Position,
        endPosition: Position
    ): RealtimeResult<EplkObject> {

        return when (other) {
            is EplkInteger ->
                RealtimeResult<EplkObject>().success(EplkInteger(value * other.value, scope))

            is EplkFloat ->
                RealtimeResult<EplkObject>().success(EplkFloat(value * other.value, scope))

            else ->
                RealtimeResult<EplkObject>().failure(EplkNotImplementedError(
                    "* operator with ${other.objectName} is not supported",
                    startPosition,
                    endPosition,
                    scope
                ))
        }
    }

    override fun operatorDivide(
        other: EplkObject,
        startPosition: Position,
        endPosition: Position
    ): RealtimeResult<EplkObject> {

        return when (other) {
            is EplkInteger ->
                RealtimeResult<EplkObject>().success(EplkFloat(value.toFloat() / other.value.toFloat(), scope))

            is EplkFloat ->
                RealtimeResult<EplkObject>().success(EplkFloat(value / other.value, scope))

            else ->
                RealtimeResult<EplkObject>().failure(EplkNotImplementedError(
                    "/ operator with ${other.objectName} is not supported",
                    startPosition,
                    endPosition,
                    scope
                ))
        }
    }

    override fun operatorPow(
        other: EplkObject,
        startPosition: Position,
        endPosition: Position
    ): RealtimeResult<EplkObject> {

        return when (other) {
            is EplkInteger ->
                RealtimeResult<EplkObject>().success(EplkFloat(value.toFloat().pow(other.value), scope))

            is EplkFloat ->
                RealtimeResult<EplkObject>().success(EplkFloat(value.toFloat().pow(other.value), scope))

            else ->
                RealtimeResult<EplkObject>().failure(EplkNotImplementedError(
                    "^ operator with ${other.objectName} is not supported",
                    startPosition,
                    endPosition,
                    scope
                ))
        }
    }
}