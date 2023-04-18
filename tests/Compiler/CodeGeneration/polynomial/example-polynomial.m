evaluate0
          sw -32(r14), r15
          % processing line71
          % processing line71
          % processing line71
          % processing line71
          % processing: tmpVar3 := 0
          addi r1,r0,0
          sw -24(r14),r1
          % processing: tmpVar2 := tmpVar3
          lw r1,-24(r14)
          sw -20(r14), r1
          % processing: tmpVar1 := tmpVar2
          lw r1,-20(r14)
          sw -16(r14), r1
          % processing: tmpVar0 := tmpVar1
          lw r1,-16(r14)
          sw -12(r14), r1
          % processing line71
          lw r1, -12(r14)
          sw -4(r14), r1
          % processing return
          lw r15, -32(r14)
          lw r14, -28(r14)
          jr r15
          lw r15, -32(r14)
          lw r14, -28(r14)
          jr r15
evaluate1
          sw -124(r14), r15
          % processing line78
          % processing line78
          % processing line78
          % processing line78
          addi r5, r14, 0
          % processing: tmpVar7 := a
          lw r1,-4(r5)
          addi r5, r1, 0 
          % processing: tmpVar7 := a
          lw r1,0(r5)
          sw -28(r14), r1
          % processing: tmpVar6 := tmpVar7
          lw r1,-28(r14)
          sw -24(r14), r1
          % processing: tmpVar5 := tmpVar6
          lw r1,-24(r14)
          sw -20(r14), r1
          % processing: tmpVar4 := tmpVar5
          lw r1,-20(r14)
          sw -16(r14), r1
          % processing assignment statement
          addi r5, r14, 0
          lw r6,-16(r5)
          sw -12(r5), r6
          % processing line79
          % processing line79
          % processing line79
          % processing line79
          addi r5, r14, 0
          % processing: tmpVar11 := result
          lw r1,-12(r5)
          sw -44(r14), r1
          % processing line79
          addi r5, r14, 0
          % processing: tmpVar13 := x
          lw r1,-8(r5)
          sw -52(r14), r1
          % processing: tmpVar10 := tmpVar11
          lw r1,-44(r14)
          sw -40(r14), r1
          % processing: tmpVar10 := tmpVar10 * tmpVar13
          lw r1,-40(r14)
          lw r2,-52(r14)
          mul r1,r1,r2
          sw -40(r14),r1
          % processing line79
          % processing line79
          addi r5, r14, 0
          % processing: tmpVar16 := b
          lw r1,-4(r5)
          addi r5, r1, 0 
          % processing: tmpVar16 := b
          lw r1,-4(r5)
          sw -64(r14), r1
          % processing: tmpVar15 := tmpVar16
          lw r1,-64(r14)
          sw -60(r14), r1
          % processing: tmpVar9 := tmpVar10
          lw r1,-40(r14)
          sw -36(r14), r1
          % processing: tmpVar9 := tmpVar9 + tmpVar15
          lw r1,-36(r14)
          lw r2,-60(r14)
          add r1,r1,r2
          sw -36(r14),r1
          % processing: tmpVar8 := tmpVar9
          lw r1,-36(r14)
          sw -32(r14), r1
          % processing assignment statement
          addi r5, r14, 0
          lw r6,-32(r5)
          sw -12(r5), r6
          % processing line80
          % processing line80
          % processing line80
          % processing line80
          addi r5, r14, 0
          % processing: tmpVar20 := result
          lw r1,-12(r5)
          sw -80(r14), r1
          % processing line80
          addi r5, r14, 0
          % processing: tmpVar22 := x
          lw r1,-8(r5)
          sw -88(r14), r1
          % processing: tmpVar19 := tmpVar20
          lw r1,-80(r14)
          sw -76(r14), r1
          % processing: tmpVar19 := tmpVar19 * tmpVar22
          lw r1,-76(r14)
          lw r2,-88(r14)
          mul r1,r1,r2
          sw -76(r14),r1
          % processing line80
          % processing line80
          addi r5, r14, 0
          % processing: tmpVar25 := c
          lw r1,-4(r5)
          addi r5, r1, 0 
          % processing: tmpVar25 := c
          lw r1,-8(r5)
          sw -100(r14), r1
          % processing: tmpVar24 := tmpVar25
          lw r1,-100(r14)
          sw -96(r14), r1
          % processing: tmpVar18 := tmpVar19
          lw r1,-76(r14)
          sw -72(r14), r1
          % processing: tmpVar18 := tmpVar18 + tmpVar24
          lw r1,-72(r14)
          lw r2,-96(r14)
          add r1,r1,r2
          sw -72(r14),r1
          % processing: tmpVar17 := tmpVar18
          lw r1,-72(r14)
          sw -68(r14), r1
          % processing assignment statement
          addi r5, r14, 0
          lw r6,-68(r5)
          sw -12(r5), r6
          % processing line81
          % processing line81
          % processing line81
          % processing line81
          addi r5, r14, 0
          % processing: tmpVar29 := result
          lw r1,-12(r5)
          sw -116(r14), r1
          % processing: tmpVar28 := tmpVar29
          lw r1,-116(r14)
          sw -112(r14), r1
          % processing: tmpVar27 := tmpVar28
          lw r1,-112(r14)
          sw -108(r14), r1
          % processing: tmpVar26 := tmpVar27
          lw r1,-108(r14)
          sw -104(r14), r1
          % processing line81
          lw r1, -104(r14)
          sw -4(r14), r1
          % processing return
          lw r15, -124(r14)
          lw r14, -120(r14)
          jr r15
          lw r15, -124(r14)
          lw r14, -120(r14)
          jr r15
constructor2
          sw -72(r14), r15
          % processing line86
          % processing line86
          % processing line86
          % processing line86
          addi r5, r14, 0
          % processing: tmpVar33 := A
          lw r1,-8(r5)
          sw -32(r14), r1
          % processing: tmpVar32 := tmpVar33
          lw r1,-32(r14)
          sw -28(r14), r1
          % processing: tmpVar31 := tmpVar32
          lw r1,-28(r14)
          sw -24(r14), r1
          % processing: tmpVar30 := tmpVar31
          lw r1,-24(r14)
          sw -20(r14), r1
          % processing assignment statement
          addi r5, r14, 0
          lw r6,-20(r5)
          lw r4, -4(r5)
          add r5, r0, r4
          sw 0(r5), r6
          % processing line87
          % processing line87
          % processing line87
          % processing line87
          addi r5, r14, 0
          % processing: tmpVar37 := B
          lw r1,-12(r5)
          sw -48(r14), r1
          % processing: tmpVar36 := tmpVar37
          lw r1,-48(r14)
          sw -44(r14), r1
          % processing: tmpVar35 := tmpVar36
          lw r1,-44(r14)
          sw -40(r14), r1
          % processing: tmpVar34 := tmpVar35
          lw r1,-40(r14)
          sw -36(r14), r1
          % processing assignment statement
          addi r5, r14, 0
          lw r6,-36(r5)
          lw r4, -4(r5)
          add r5, r0, r4
          sw -4(r5), r6
          % processing line88
          % processing line88
          % processing line88
          % processing line88
          addi r5, r14, 0
          % processing: tmpVar41 := C
          lw r1,-16(r5)
          sw -64(r14), r1
          % processing: tmpVar40 := tmpVar41
          lw r1,-64(r14)
          sw -60(r14), r1
          % processing: tmpVar39 := tmpVar40
          lw r1,-60(r14)
          sw -56(r14), r1
          % processing: tmpVar38 := tmpVar39
          lw r1,-56(r14)
          sw -52(r14), r1
          % processing assignment statement
          addi r5, r14, 0
          lw r6,-52(r5)
          lw r4, -4(r5)
          add r5, r0, r4
          sw -8(r5), r6
          lw r15, -72(r14)
          lw r14, -68(r14)
          jr r15
constructor3
          sw -52(r14), r15
          % processing line93
          % processing line93
          % processing line93
          % processing line93
          addi r5, r14, 0
          % processing: tmpVar45 := A
          lw r1,-8(r5)
          sw -28(r14), r1
          % processing: tmpVar44 := tmpVar45
          lw r1,-28(r14)
          sw -24(r14), r1
          % processing: tmpVar43 := tmpVar44
          lw r1,-24(r14)
          sw -20(r14), r1
          % processing: tmpVar42 := tmpVar43
          lw r1,-20(r14)
          sw -16(r14), r1
          % processing assignment statement
          addi r5, r14, 0
          lw r6,-16(r5)
          lw r4, -4(r5)
          add r5, r0, r4
          sw 0(r5), r6
          % processing line94
          % processing line94
          % processing line94
          % processing line94
          addi r5, r14, 0
          % processing: tmpVar49 := B
          lw r1,-12(r5)
          sw -44(r14), r1
          % processing: tmpVar48 := tmpVar49
          lw r1,-44(r14)
          sw -40(r14), r1
          % processing: tmpVar47 := tmpVar48
          lw r1,-40(r14)
          sw -36(r14), r1
          % processing: tmpVar46 := tmpVar47
          lw r1,-36(r14)
          sw -32(r14), r1
          % processing assignment statement
          addi r5, r14, 0
          lw r6,-32(r5)
          lw r4, -4(r5)
          add r5, r0, r4
          sw -4(r5), r6
          lw r15, -52(r14)
          lw r14, -48(r14)
          jr r15
evaluate4
          sw -88(r14), r15
          % processing line100
          % processing line100
          % processing line100
          % processing line100
          % processing: tmpVar53 := 0
          addi r1,r0,0
          sw -28(r14),r1
          % processing: tmpVar52 := tmpVar53
          lw r1,-28(r14)
          sw -24(r14), r1
          % processing: tmpVar51 := tmpVar52
          lw r1,-24(r14)
          sw -20(r14), r1
          % processing: tmpVar50 := tmpVar51
          lw r1,-20(r14)
          sw -16(r14), r1
          % processing assignment statement
          addi r5, r14, 0
          lw r6,-16(r5)
          sw -12(r5), r6
          % processing line101
          % processing line101
          % processing line101
          % processing line101
          addi r5, r14, 0
          % processing: tmpVar57 := a
          lw r1,-4(r5)
          addi r5, r1, 0 
          % processing: tmpVar57 := a
          lw r1,0(r5)
          sw -44(r14), r1
          % processing line101
          addi r5, r14, 0
          % processing: tmpVar59 := x
          lw r1,-8(r5)
          sw -52(r14), r1
          % processing: tmpVar56 := tmpVar57
          lw r1,-44(r14)
          sw -40(r14), r1
          % processing: tmpVar56 := tmpVar56 * tmpVar59
          lw r1,-40(r14)
          lw r2,-52(r14)
          mul r1,r1,r2
          sw -40(r14),r1
          % processing line101
          % processing line101
          addi r5, r14, 0
          % processing: tmpVar62 := b
          lw r1,-4(r5)
          addi r5, r1, 0 
          % processing: tmpVar62 := b
          lw r1,-4(r5)
          sw -64(r14), r1
          % processing: tmpVar61 := tmpVar62
          lw r1,-64(r14)
          sw -60(r14), r1
          % processing: tmpVar55 := tmpVar56
          lw r1,-40(r14)
          sw -36(r14), r1
          % processing: tmpVar55 := tmpVar55 + tmpVar61
          lw r1,-36(r14)
          lw r2,-60(r14)
          add r1,r1,r2
          sw -36(r14),r1
          % processing: tmpVar54 := tmpVar55
          lw r1,-36(r14)
          sw -32(r14), r1
          % processing assignment statement
          addi r5, r14, 0
          lw r6,-32(r5)
          sw -12(r5), r6
          % processing line102
          % processing line102
          % processing line102
          % processing line102
          addi r5, r14, 0
          % processing: tmpVar66 := result
          lw r1,-12(r5)
          sw -80(r14), r1
          % processing: tmpVar65 := tmpVar66
          lw r1,-80(r14)
          sw -76(r14), r1
          % processing: tmpVar64 := tmpVar65
          lw r1,-76(r14)
          sw -72(r14), r1
          % processing: tmpVar63 := tmpVar64
          lw r1,-72(r14)
          sw -68(r14), r1
          % processing line102
          lw r1, -68(r14)
          sw -4(r14), r1
          % processing return
          lw r15, -88(r14)
          lw r14, -84(r14)
          jr r15
          lw r15, -88(r14)
          lw r14, -84(r14)
          jr r15
entry
          addi r14, r0, topaddr
          % processing pointer
          addi r1, r0, -260
          add r1, r1, r14
          sw -4(r14), r1
          % processing pointer
          addi r1, r0, -268
          add r1, r1, r14
          sw -8(r14), r1
          % processing line108
          % processing line108
          % processing line108
          % processing line108
          % processing: tmpVar70 := 2
          addi r1,r0,2
          sw -28(r14),r1
          % processing: tmpVar69 := tmpVar70
          lw r1,-28(r14)
          sw -24(r14), r1
          % processing: tmpVar68 := tmpVar69
          lw r1,-24(r14)
          sw -20(r14), r1
          % processing: tmpVar67 := tmpVar68
          lw r1,-20(r14)
          sw -16(r14), r1
          % processing line108
          % processing line108
          % processing line108
          % processing line108
          % processing: tmpVar74 := 3
          addi r1,r0,3
          sw -44(r14),r1
          % processing: tmpVar73 := tmpVar74
          lw r1,-44(r14)
          sw -40(r14), r1
          % processing: tmpVar72 := tmpVar73
          lw r1,-40(r14)
          sw -36(r14), r1
          % processing: tmpVar71 := tmpVar72
          lw r1,-36(r14)
          sw -32(r14), r1
          lw r1, -4(r14)
          sw -284(r14), r1
          %processing parameter: A
          lw r1, -16(r14)
          sw -288(r14), r1
          %processing parameter: B
          lw r1, -32(r14)
          sw -292(r14), r1
          % processing jump
          sw -328(r14), r14
          addi r14, r14, -280
          jl r15, constructor3
          % processing line109
          % processing line109
          % processing line109
          % processing line109
          % processing: tmpVar78 := 2
          addi r1,r0,2
          sw -60(r14),r1
          % processing: tmpVar77 := tmpVar78
          lw r1,-60(r14)
          sw -56(r14), r1
          % processing: tmpVar76 := tmpVar77
          lw r1,-56(r14)
          sw -52(r14), r1
          % processing: tmpVar75 := tmpVar76
          lw r1,-52(r14)
          sw -48(r14), r1
          % processing line109
          % processing line109
          % processing line109
          % processing line109
          % processing: tmpVar82 := 1
          addi r1,r0,1
          sw -76(r14),r1
          % processing: tmpVar81 := tmpVar82
          lw r1,-76(r14)
          sw -72(r14), r1
          % processing: tmpVar80 := tmpVar81
          lw r1,-72(r14)
          sw -68(r14), r1
          % processing: tmpVar79 := tmpVar80
          lw r1,-68(r14)
          sw -64(r14), r1
          % processing line109
          % processing line109
          % processing line109
          % processing line109
          % processing: tmpVar86 := 0
          addi r1,r0,0
          sw -92(r14),r1
          % processing: tmpVar85 := tmpVar86
          lw r1,-92(r14)
          sw -88(r14), r1
          % processing: tmpVar84 := tmpVar85
          lw r1,-88(r14)
          sw -84(r14), r1
          % processing: tmpVar83 := tmpVar84
          lw r1,-84(r14)
          sw -80(r14), r1
          lw r1, -8(r14)
          sw -284(r14), r1
          %processing parameter: A
          lw r1, -48(r14)
          sw -288(r14), r1
          %processing parameter: B
          lw r1, -64(r14)
          sw -292(r14), r1
          %processing parameter: C
          lw r1, -80(r14)
          sw -296(r14), r1
          % processing jump
          sw -348(r14), r14
          addi r14, r14, -280
          jl r15, constructor2
          % processing line112
          % processing line112
          % processing line112
          % processing line112
          % processing: tmpVar90 := 1
          addi r1,r0,1
          sw -108(r14),r1
          % processing: tmpVar89 := tmpVar90
          lw r1,-108(r14)
          sw -104(r14), r1
          % processing: tmpVar88 := tmpVar89
          lw r1,-104(r14)
          sw -100(r14), r1
          % processing: tmpVar87 := tmpVar88
          lw r1,-100(r14)
          sw -96(r14), r1
          % processing assignment statement
          addi r5, r14, 0
          lw r6,-96(r5)
          sw -12(r5), r6
          % processing line113
gowhile1  
          % processing line113
          % processing line113
          % processing line113
          % processing line113
          addi r6, r14, 0
          % processing: tmpVar94 := counter
          lw r2,-12(r6)
          sw -124(r14), r2
          % processing: tmpVar93 := tmpVar94
          lw r2,-124(r14)
          sw -120(r14), r2
          % processing: tmpVar92 := tmpVar93
          lw r2,-120(r14)
          sw -116(r14), r2
          % processing line113
          % processing line113
          % processing line113
          % processing: tmpVar98 := 10
          addi r2,r0,10
          sw -140(r14),r2
          % processing: tmpVar97 := tmpVar98
          lw r2,-140(r14)
          sw -136(r14), r2
          % processing: tmpVar96 := tmpVar97
          lw r2,-136(r14)
          sw -132(r14), r2
          % processing: tmpVar91 := tmpVar92
          lw r2,-116(r14)
          sw -112(r14), r2
          % processing: tmpVar91 := tmpVar91 leq tmpVar96
          lw r2,-112(r14)
          lw r3,-132(r14)
          cle r2,r2,r3
          sw -112(r14),r2
          lw r1, -112(r14)
          bz r1, endwhile1
          % processing line115
          % processing write statement
          % processing line115
          % processing line115
          % processing line115
          % processing line115
          addi r6, r14, 0
          % processing: tmpVar102 := counter
          lw r2,-12(r6)
          sw -156(r14), r2
          % processing: tmpVar101 := tmpVar102
          lw r2,-156(r14)
          sw -152(r14), r2
          % processing: tmpVar100 := tmpVar101
          lw r2,-152(r14)
          sw -148(r14), r2
          % processing: tmpVar99 := tmpVar100
          lw r2,-148(r14)
          sw -144(r14), r2
          lw r2, -144(r14)
          addi r14, r14, -280
          sw -8(r14), r2
          addi r2, r0, buf
          sw -12(r14), r2
          jl r15, intstr
          sw -8(r14),r13
          jl r15, putstr
          subi r14, r14, -280
          addi r2, r0, charLineSep
          addi r14, r14, -280
          sw -8(r14), r2
          jl r15, putstr
          subi r14, r14, -280
          % processing line116
          % processing write statement
          % processing line116
          % processing line116
          % processing line116
          % processing line116
          % processing line116
          % processing line116
          % processing line116
          % processing line116
          addi r6, r14, 0
          % processing: tmpVar110 := counter
          lw r2,-12(r6)
          sw -188(r14), r2
          % processing: tmpVar109 := tmpVar110
          lw r2,-188(r14)
          sw -184(r14), r2
          % processing: tmpVar108 := tmpVar109
          lw r2,-184(r14)
          sw -180(r14), r2
          % processing: tmpVar107 := tmpVar108
          lw r2,-180(r14)
          sw -176(r14), r2
          addi r6, r14, 0
          lw r5, -4(r6)
          add r6, r0, r5
          sw -172(r14), r5
          sw -284(r14), r6
          %processing parameter1: x
          lw r2, -176(r14)
          sw -288(r14), r2
          % processing jump
          sw -364(r14), r14
          addi r14, r14, -280
          jl r15, evaluate4
          lw r2, -284(r14)
          sw -172(r14), r2
          % processing: tmpVar105 := tmpVar106
          lw r2,-172(r14)
          sw -168(r14), r2
          % processing: tmpVar104 := tmpVar105
          lw r2,-168(r14)
          sw -164(r14), r2
          % processing: tmpVar103 := tmpVar104
          lw r2,-164(r14)
          sw -160(r14), r2
          lw r2, -160(r14)
          addi r14, r14, -280
          sw -8(r14), r2
          addi r2, r0, buf
          sw -12(r14), r2
          jl r15, intstr
          sw -8(r14),r13
          jl r15, putstr
          subi r14, r14, -280
          addi r2, r0, charLineSep
          addi r14, r14, -280
          sw -8(r14), r2
          jl r15, putstr
          subi r14, r14, -280
          % processing line117
          % processing write statement
          % processing line117
          % processing line117
          % processing line117
          % processing line117
          % processing line117
          % processing line117
          % processing line117
          % processing line117
          addi r6, r14, 0
          % processing: tmpVar118 := counter
          lw r2,-12(r6)
          sw -220(r14), r2
          % processing: tmpVar117 := tmpVar118
          lw r2,-220(r14)
          sw -216(r14), r2
          % processing: tmpVar116 := tmpVar117
          lw r2,-216(r14)
          sw -212(r14), r2
          % processing: tmpVar115 := tmpVar116
          lw r2,-212(r14)
          sw -208(r14), r2
          addi r6, r14, 0
          lw r5, -8(r6)
          add r6, r0, r5
          sw -204(r14), r5
          sw -284(r14), r6
          %processing parameter1: x
          lw r2, -208(r14)
          sw -288(r14), r2
          % processing jump
          sw -400(r14), r14
          addi r14, r14, -280
          jl r15, evaluate1
          lw r2, -284(r14)
          sw -204(r14), r2
          % processing: tmpVar113 := tmpVar114
          lw r2,-204(r14)
          sw -200(r14), r2
          % processing: tmpVar112 := tmpVar113
          lw r2,-200(r14)
          sw -196(r14), r2
          % processing: tmpVar111 := tmpVar112
          lw r2,-196(r14)
          sw -192(r14), r2
          lw r2, -192(r14)
          addi r14, r14, -280
          sw -8(r14), r2
          addi r2, r0, buf
          sw -12(r14), r2
          jl r15, intstr
          sw -8(r14),r13
          jl r15, putstr
          subi r14, r14, -280
          addi r2, r0, charLineSep
          addi r14, r14, -280
          sw -8(r14), r2
          jl r15, putstr
          subi r14, r14, -280
          % processing line118
          % processing line118
          % processing line118
          % processing line118
          addi r6, r14, 0
          % processing: tmpVar122 := counter
          lw r2,-12(r6)
          sw -236(r14), r2
          % processing: tmpVar121 := tmpVar122
          lw r2,-236(r14)
          sw -232(r14), r2
          % processing line118
          % processing line118
          % processing: tmpVar125 := 1
          addi r2,r0,1
          sw -248(r14),r2
          % processing: tmpVar124 := tmpVar125
          lw r2,-248(r14)
          sw -244(r14), r2
          % processing: tmpVar120 := tmpVar121
          lw r2,-232(r14)
          sw -228(r14), r2
          % processing: tmpVar120 := tmpVar120 + tmpVar124
          lw r2,-228(r14)
          lw r3,-244(r14)
          add r2,r2,r3
          sw -228(r14),r2
          % processing: tmpVar119 := tmpVar120
          lw r2,-228(r14)
          sw -224(r14), r2
          % processing assignment statement
          addi r6, r14, 0
          lw r7,-224(r6)
          sw -12(r6), r7
          j gowhile1
endwhile1 
hlt

floatAdd
          lw r1, -4(r14)
          lw r2, -8(r14)
          lw r3, -12(r14)
          lw r4, -16(r14)
gowhileFAdd
          cne r5, r2, r4
          bz r5, endwhileFAdd
          clt r6, r2, r4
          bz r6, elseFAdd
          muli r3, r3, 10
          subi r4, r4, 1
          j endIfFAdd
elseFAdd
          muli r1, r1, 10
          subi r2, r2, 1
endIfFAdd
          j gowhileFAdd
endwhileFAdd
          add  r1, r1, r3
          sw   -4(r14), r1
          sw   -8(r14), r2
          jr   r15
floatMul
          lw r1,  -4(r14)
          lw r2,  -8(r14)
          lw r3,  -12(r14)
          lw r4,  -16(r14)
          mul r1, r1, r3
          add r2, r2, r4
          sw  -4(r14), r1
          sw  -8(r14), r2
          jr   r15
floatLeq
          lw r1,  -4(r14)
          lw r2,  -8(r14)
          lw r3,  -12(r14)
          lw r4,  -16(r14)
gowhileFLeq
          cne r5, r2, r4
          bz   r5, endwhileFLeq
          clt r6, r2, r4
          bz r6, elseFLeq
          muli r3, r3, 10
          subi r4, r4, 1
          j endIfFLeq
elseFLeq
          muli r1, r1, 10
          subi r2, r2, 1
          endIfFLeq
          j gowhileFLeq
endwhileFLeq 
          cle r1, r1, r3
          sw -4(r14), r1
          jr r15

buf       res 20
charE     db "e", 0
charLineSep    db "  ", 0
