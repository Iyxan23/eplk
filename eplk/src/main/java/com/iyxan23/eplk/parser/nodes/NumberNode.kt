package com.iyxan23.eplk.parser.nodes

import com.iyxan23.eplk.Token

// Simply a number, example: 1
data class NumberNode(
    val number: Token
) : Node()