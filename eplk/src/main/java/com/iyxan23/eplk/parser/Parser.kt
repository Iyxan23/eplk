package com.iyxan23.eplk.parser

import com.iyxan23.eplk.Token
import com.iyxan23.eplk.Tokens
import com.iyxan23.eplk.errors.SyntaxError
import com.iyxan23.eplk.parser.nodes.BinOpNode
import com.iyxan23.eplk.parser.nodes.Node
import com.iyxan23.eplk.parser.nodes.NumberNode

class Parser(val tokens: ArrayList<Token>) {

    private var currentToken: Token = tokens[0]
    private var indexToken = 0

    fun parse(): Node {
        return expression().node!!
    }

    private fun advance(): Token {
        indexToken++

        if (indexToken < tokens.size) {
            currentToken = tokens[indexToken]
        }

        return currentToken
    }

    private val numberLiterals = arrayOf(Tokens.INT_LITERAL, Tokens.FLOAT_LITERAL)

    // INT|FLOAT
    private fun factor(): ParseResult {
        val result = ParseResult()
        val curToken = currentToken

        if (numberLiterals.contains(curToken.token)) {
            result.register(advance())
            return result.success(NumberNode(curToken))
        }

        return result.failure(SyntaxError(
            "Expected integer or float literals",
            TODO(),
            TODO()
        ))
    }

    private val termOperators = arrayOf(Tokens.MUL, Tokens.DIV)

    // factor ((MUL|DIV) factor)*
    private fun term(): ParseResult {
        val result = ParseResult()
        var leftNode = result.register(factor()) as Node

        if (result.error == null) return result

        while (termOperators.contains(currentToken.token)) {
            val operatorToken = currentToken
            result.register(advance())
            val rightNode = result.register(factor()) as Node
            leftNode = BinOpNode(leftNode, operatorToken, rightNode)
        }

        return result.success(leftNode)
    }

    private val expressionOperators = arrayOf(Tokens.PLUS, Tokens.MINUS)

    // term ((PLUS|MINUS) term)*
    private fun expression(): ParseResult {
        val result = ParseResult()
        var leftNode = result.register(term()) as Node

        while (expressionOperators.contains(currentToken.token)) {
            val operatorToken = currentToken
            result.register(advance())
            val rightNode = result.register(term()) as Node
            leftNode =  BinOpNode(leftNode, operatorToken, rightNode)
        }

        return result.success(leftNode)
    }
}
