add0
          sw -80(r14), r15
          % processing line50
          % processing line50
          % processing line50
          % processing line50
          % processing line50
          % processing line50
          % processing line50
          % processing line50
          addi r5, r14, 0
          % processing: tmpVar8 := x
          lw r1,-4(r5)
          sw -48(r14), r1
          % processing: tmpVar7 := tmpVar8
          lw r1,-48(r14)
          sw -44(r14), r1
          % processing: tmpVar6 := tmpVar7
          lw r1,-44(r14)
          sw -40(r14), r1
          % processing: tmpVar5 := tmpVar6
          lw r1,-40(r14)
          sw -36(r14), r1
          addi r5, r14, 0
          %processing parameter0: x
          lw r1, -36(r14)
          sw -88(r14), r1
          % processing jump
          sw -116(r14), r14
          addi r14, r14, -84
          jl r15, multi21
          lw r1, -88(r14)
          sw -28(r14), r1
          % processing: tmpVar2 := tmpVar3
          lw r1,-28(r14)
          sw -24(r14), r1
          % processing line50
          % processing line50
          addi r5, r14, 0
          % processing: tmpVar11 := y
          lw r1,-8(r5)
          sw -60(r14), r1
          % processing: tmpVar10 := tmpVar11
          lw r1,-60(r14)
          sw -56(r14), r1
          % processing line50
          % processing line50
          addi r5, r14, 0
          % processing: tmpVar14 := z
          lw r1,-12(r5)
          sw -72(r14), r1
          % processing: tmpVar13 := tmpVar14
          lw r1,-72(r14)
          sw -68(r14), r1
          % processing: tmpVar1 := tmpVar2
          lw r1,-24(r14)
          sw -20(r14), r1
          % processing: tmpVar1 := tmpVar1 + tmpVar10
          lw r1,-20(r14)
          lw r2,-56(r14)
          add r1,r1,r2
          sw -20(r14),r1
          % processing: tmpVar1 := tmpVar1 + tmpVar13
          lw r1,-20(r14)
          lw r2,-68(r14)
          add r1,r1,r2
          sw -20(r14),r1
          % processing: tmpVar0 := tmpVar1
          lw r1,-20(r14)
          sw -16(r14), r1
          % processing line50
          lw r1, -16(r14)
          sw -4(r14), r1
          % processing return
          lw r15, -80(r14)
          lw r14, -76(r14)
          jr r15
          lw r15, -80(r14)
          lw r14, -76(r14)
          jr r15
multi21
          sw -36(r14), r15
          % processing line55
          % processing line55
          % processing line55
          % processing line55
          % processing: tmpVar18 := 2
          addi r1,r0,2
          sw -20(r14),r1
          % processing line55
          addi r5, r14, 0
          % processing: tmpVar20 := x
          lw r1,-4(r5)
          sw -28(r14), r1
          % processing: tmpVar17 := tmpVar18
          lw r1,-20(r14)
          sw -16(r14), r1
          % processing: tmpVar17 := tmpVar17 * tmpVar20
          lw r1,-16(r14)
          lw r2,-28(r14)
          mul r1,r1,r2
          sw -16(r14),r1
          % processing: tmpVar16 := tmpVar17
          lw r1,-16(r14)
          sw -12(r14), r1
          % processing: tmpVar15 := tmpVar16
          lw r1,-12(r14)
          sw -8(r14), r1
          % processing line55
          lw r1, -8(r14)
          sw -4(r14), r1
          % processing return
          lw r15, -36(r14)
          lw r14, -32(r14)
          jr r15
          lw r15, -36(r14)
          lw r14, -32(r14)
          jr r15
entry
          addi r14, r0, topaddr
          % processing pointer
          addi r1, r0, -612
          add r1, r1, r14
          sw -16(r14), r1
          % processing pointer
          addi r1, r0, -640
          add r1, r1, r14
          sw -20(r14), r1
          % processing pointer
          addi r1, r0, -652
          add r1, r1, r14
          sw -24(r14), r1
          % processing line72
          % processing line72
          % processing line72
          % processing: tmpVar23 := 0
          addi r1,r0,0
          sw -36(r14),r1
          % processing: tmpVar22 := tmpVar23
          lw r1,-36(r14)
          sw -32(r14), r1
          % processing: tmpVar21 := tmpVar22
          lw r1,-32(r14)
          sw -28(r14), r1
          % processing line72
          % processing line72
          % processing line72
          % processing line72
          % processing: tmpVar27 := 64
          addi r1,r0,64
          sw -52(r14),r1
          % processing: tmpVar26 := tmpVar27
          lw r1,-52(r14)
          sw -48(r14), r1
          % processing: tmpVar25 := tmpVar26
          lw r1,-48(r14)
          sw -44(r14), r1
          % processing: tmpVar24 := tmpVar25
          lw r1,-44(r14)
          sw -40(r14), r1
          % processing assignment statement
          addi r5, r14, 0
          lw r6,-40(r5)
          addi r1, r0, 0 
          addi r2, r0, 4 
          % processing: dimension 0
          lw r3, -28(r5)
          add r1, r1, r3
          mul r1, r1, r2
          muli r2, r2, 7 
          muli r1, r1, -1
          lw r4, -16(r5)
          add r1, r4, r1
          sw  0(r1), r6
          % processing line73
          % processing line73
          % processing line73
          % processing: tmpVar30 := 1
          addi r1,r0,1
          sw -64(r14),r1
          % processing: tmpVar29 := tmpVar30
          lw r1,-64(r14)
          sw -60(r14), r1
          % processing: tmpVar28 := tmpVar29
          lw r1,-60(r14)
          sw -56(r14), r1
          % processing line73
          % processing line73
          % processing line73
          % processing line73
          % processing: tmpVar34 := 34
          addi r1,r0,34
          sw -80(r14),r1
          % processing: tmpVar33 := tmpVar34
          lw r1,-80(r14)
          sw -76(r14), r1
          % processing: tmpVar32 := tmpVar33
          lw r1,-76(r14)
          sw -72(r14), r1
          % processing: tmpVar31 := tmpVar32
          lw r1,-72(r14)
          sw -68(r14), r1
          % processing assignment statement
          addi r5, r14, 0
          lw r6,-68(r5)
          addi r1, r0, 0 
          addi r2, r0, 4 
          % processing: dimension 0
          lw r3, -56(r5)
          add r1, r1, r3
          mul r1, r1, r2
          muli r2, r2, 7 
          muli r1, r1, -1
          lw r4, -16(r5)
          add r1, r4, r1
          sw  0(r1), r6
          % processing line74
          % processing line74
          % processing line74
          % processing: tmpVar37 := 2
          addi r1,r0,2
          sw -92(r14),r1
          % processing: tmpVar36 := tmpVar37
          lw r1,-92(r14)
          sw -88(r14), r1
          % processing: tmpVar35 := tmpVar36
          lw r1,-88(r14)
          sw -84(r14), r1
          % processing line74
          % processing line74
          % processing line74
          % processing line74
          % processing: tmpVar41 := 25
          addi r1,r0,25
          sw -108(r14),r1
          % processing: tmpVar40 := tmpVar41
          lw r1,-108(r14)
          sw -104(r14), r1
          % processing: tmpVar39 := tmpVar40
          lw r1,-104(r14)
          sw -100(r14), r1
          % processing: tmpVar38 := tmpVar39
          lw r1,-100(r14)
          sw -96(r14), r1
          % processing assignment statement
          addi r5, r14, 0
          lw r6,-96(r5)
          addi r1, r0, 0 
          addi r2, r0, 4 
          % processing: dimension 0
          lw r3, -84(r5)
          add r1, r1, r3
          mul r1, r1, r2
          muli r2, r2, 7 
          muli r1, r1, -1
          lw r4, -16(r5)
          add r1, r4, r1
          sw  0(r1), r6
          % processing line75
          % processing line75
          % processing line75
          % processing: tmpVar44 := 3
          addi r1,r0,3
          sw -120(r14),r1
          % processing: tmpVar43 := tmpVar44
          lw r1,-120(r14)
          sw -116(r14), r1
          % processing: tmpVar42 := tmpVar43
          lw r1,-116(r14)
          sw -112(r14), r1
          % processing line75
          % processing line75
          % processing line75
          % processing line75
          % processing: tmpVar48 := 12
          addi r1,r0,12
          sw -136(r14),r1
          % processing: tmpVar47 := tmpVar48
          lw r1,-136(r14)
          sw -132(r14), r1
          % processing: tmpVar46 := tmpVar47
          lw r1,-132(r14)
          sw -128(r14), r1
          % processing: tmpVar45 := tmpVar46
          lw r1,-128(r14)
          sw -124(r14), r1
          % processing assignment statement
          addi r5, r14, 0
          lw r6,-124(r5)
          addi r1, r0, 0 
          addi r2, r0, 4 
          % processing: dimension 0
          lw r3, -112(r5)
          add r1, r1, r3
          mul r1, r1, r2
          muli r2, r2, 7 
          muli r1, r1, -1
          lw r4, -16(r5)
          add r1, r4, r1
          sw  0(r1), r6
          % processing line76
          % processing line76
          % processing line76
          % processing: tmpVar51 := 4
          addi r1,r0,4
          sw -148(r14),r1
          % processing: tmpVar50 := tmpVar51
          lw r1,-148(r14)
          sw -144(r14), r1
          % processing: tmpVar49 := tmpVar50
          lw r1,-144(r14)
          sw -140(r14), r1
          % processing line76
          % processing line76
          % processing line76
          % processing line76
          % processing: tmpVar55 := 22
          addi r1,r0,22
          sw -164(r14),r1
          % processing: tmpVar54 := tmpVar55
          lw r1,-164(r14)
          sw -160(r14), r1
          % processing: tmpVar53 := tmpVar54
          lw r1,-160(r14)
          sw -156(r14), r1
          % processing: tmpVar52 := tmpVar53
          lw r1,-156(r14)
          sw -152(r14), r1
          % processing assignment statement
          addi r5, r14, 0
          lw r6,-152(r5)
          addi r1, r0, 0 
          addi r2, r0, 4 
          % processing: dimension 0
          lw r3, -140(r5)
          add r1, r1, r3
          mul r1, r1, r2
          muli r2, r2, 7 
          muli r1, r1, -1
          lw r4, -16(r5)
          add r1, r4, r1
          sw  0(r1), r6
          % processing line77
          % processing line77
          % processing line77
          % processing: tmpVar58 := 5
          addi r1,r0,5
          sw -176(r14),r1
          % processing: tmpVar57 := tmpVar58
          lw r1,-176(r14)
          sw -172(r14), r1
          % processing: tmpVar56 := tmpVar57
          lw r1,-172(r14)
          sw -168(r14), r1
          % processing line77
          % processing line77
          % processing line77
          % processing line77
          % processing: tmpVar62 := 11
          addi r1,r0,11
          sw -192(r14),r1
          % processing: tmpVar61 := tmpVar62
          lw r1,-192(r14)
          sw -188(r14), r1
          % processing: tmpVar60 := tmpVar61
          lw r1,-188(r14)
          sw -184(r14), r1
          % processing: tmpVar59 := tmpVar60
          lw r1,-184(r14)
          sw -180(r14), r1
          % processing assignment statement
          addi r5, r14, 0
          lw r6,-180(r5)
          addi r1, r0, 0 
          addi r2, r0, 4 
          % processing: dimension 0
          lw r3, -168(r5)
          add r1, r1, r3
          mul r1, r1, r2
          muli r2, r2, 7 
          muli r1, r1, -1
          lw r4, -16(r5)
          add r1, r4, r1
          sw  0(r1), r6
          % processing line78
          % processing line78
          % processing line78
          % processing: tmpVar65 := 6
          addi r1,r0,6
          sw -204(r14),r1
          % processing: tmpVar64 := tmpVar65
          lw r1,-204(r14)
          sw -200(r14), r1
          % processing: tmpVar63 := tmpVar64
          lw r1,-200(r14)
          sw -196(r14), r1
          % processing line78
          % processing line78
          % processing line78
          % processing line78
          % processing: tmpVar69 := 90
          addi r1,r0,90
          sw -220(r14),r1
          % processing: tmpVar68 := tmpVar69
          lw r1,-220(r14)
          sw -216(r14), r1
          % processing: tmpVar67 := tmpVar68
          lw r1,-216(r14)
          sw -212(r14), r1
          % processing: tmpVar66 := tmpVar67
          lw r1,-212(r14)
          sw -208(r14), r1
          % processing assignment statement
          addi r5, r14, 0
          lw r6,-208(r5)
          addi r1, r0, 0 
          addi r2, r0, 4 
          % processing: dimension 0
          lw r3, -196(r5)
          add r1, r1, r3
          mul r1, r1, r2
          muli r2, r2, 7 
          muli r1, r1, -1
          lw r4, -16(r5)
          add r1, r4, r1
          sw  0(r1), r6
          % processing line79
          % processing line79
          % processing line79
          % processing line79
          % processing: tmpVar73 := 1
          addi r1,r0,1
          sw -236(r14),r1
          % processing: tmpVar72 := tmpVar73
          lw r1,-236(r14)
          sw -232(r14), r1
          % processing line79
          % processing line79
          % processing: tmpVar76 := 2
          addi r1,r0,2
          sw -248(r14),r1
          % processing line79
          % processing: tmpVar78 := 3
          addi r1,r0,3
          sw -256(r14),r1
          % processing: tmpVar75 := tmpVar76
          lw r1,-248(r14)
          sw -244(r14), r1
          % processing: tmpVar75 := tmpVar75 * tmpVar78
          lw r1,-244(r14)
          lw r2,-256(r14)
          mul r1,r1,r2
          sw -244(r14),r1
          % processing: tmpVar71 := tmpVar72
          lw r1,-232(r14)
          sw -228(r14), r1
          % processing: tmpVar71 := tmpVar71 + tmpVar75
          lw r1,-228(r14)
          lw r2,-244(r14)
          add r1,r1,r2
          sw -228(r14),r1
          % processing: tmpVar70 := tmpVar71
          lw r1,-228(r14)
          sw -224(r14), r1
          % processing assignment statement
          addi r5, r14, 0
          lw r6,-224(r5)
          sw -8(r5), r6
          % processing line80
          % processing write statement
          % processing line80
          % processing line80
          % processing line80
          % processing line80
          % processing line80
          % processing line80
          % processing line80
          addi r5, r14, 0
          % processing: tmpVar85 := y
          lw r1,-8(r5)
          sw -284(r14), r1
          % processing: tmpVar84 := tmpVar85
          lw r1,-284(r14)
          sw -280(r14), r1
          % processing line80
          % processing line80
          % processing: tmpVar88 := 4
          addi r1,r0,4
          sw -296(r14),r1
          % processing: tmpVar87 := tmpVar88
          lw r1,-296(r14)
          sw -292(r14), r1
          % processing: tmpVar83 := tmpVar84
          lw r1,-280(r14)
          sw -276(r14), r1
          % processing: tmpVar83 := tmpVar83 - tmpVar87
          lw r1,-276(r14)
          lw r2,-292(r14)
          sub r1,r1,r2
          sw -276(r14),r1
          addi r5, r14, 0
          % processing: tmpVar82 := arr
          addi r1, r0, 0 
          addi r2, r0, 4 
          % processing: dimension 0
          lw r3, -276(r5)
          add r1, r1, r3
          mul r1, r1, r2 
          muli r2, r2, 7 
          muli r1, r1, -1
          lw r4, -16(r5)
          add r1, r4, r1
          lw r3, 0(r1)
          sw -272(r14), r3
          % processing: tmpVar81 := tmpVar82
          lw r1,-272(r14)
          sw -268(r14), r1
          % processing: tmpVar80 := tmpVar81
          lw r1,-268(r14)
          sw -264(r14), r1
          % processing: tmpVar79 := tmpVar80
          lw r1,-264(r14)
          sw -260(r14), r1
          lw r1, -260(r14)
          addi r14, r14, -736
          sw -8(r14), r1
          addi r1, r0, buf
          sw -12(r14), r1
          jl r15, intstr
          sw -8(r14),r13
          jl r15, putstr
          subi r14, r14, -736
          addi r1, r0, charLineSep
          addi r14, r14, -736
          sw -8(r14), r1
          jl r15, putstr
          subi r14, r14, -736
          % processing line81
          % processing read statement
          addi r5, r14, 0
          addi r1, r0, buf
          addi r14, r14, -736
          sw -8(r14), r1
          jl r15,getstr
          jl r15,strint
          addi r6, r13, 0
          subi r14, r14, -736
          sw -4(r5), r6
          % processing line82
          % processing line82
          % processing line82
          % processing line82
          % processing: tmpVar92 := 1
          addi r1,r0,1
          sw -312(r14),r1
          % processing: tmpVar91 := tmpVar92
          lw r1,-312(r14)
          sw -308(r14), r1
          % processing: tmpVar90 := tmpVar91
          lw r1,-308(r14)
          sw -304(r14), r1
          % processing: tmpVar89 := tmpVar90
          lw r1,-304(r14)
          sw -300(r14), r1
          % processing assignment statement
          addi r5, r14, 0
          lw r6,-300(r5)
          sw -12(r5), r6
          % processing line83
          % processing line83
          % processing line83
          % processing line83
          % processing line83
          % processing line83
          % processing line83
          % processing line83
          addi r5, r14, 0
          % processing: tmpVar101 := x
          lw r1,-4(r5)
          sw -348(r14), r1
          % processing: tmpVar100 := tmpVar101
          lw r1,-348(r14)
          sw -344(r14), r1
          % processing: tmpVar99 := tmpVar100
          lw r1,-344(r14)
          sw -340(r14), r1
          % processing: tmpVar98 := tmpVar99
          lw r1,-340(r14)
          sw -336(r14), r1
          % processing line83
          % processing line83
          % processing line83
          % processing line83
          addi r5, r14, 0
          % processing: tmpVar105 := y
          lw r1,-8(r5)
          sw -364(r14), r1
          % processing: tmpVar104 := tmpVar105
          lw r1,-364(r14)
          sw -360(r14), r1
          % processing: tmpVar103 := tmpVar104
          lw r1,-360(r14)
          sw -356(r14), r1
          % processing: tmpVar102 := tmpVar103
          lw r1,-356(r14)
          sw -352(r14), r1
          % processing line83
          % processing line83
          % processing line83
          % processing line83
          addi r5, r14, 0
          % processing: tmpVar109 := z
          lw r1,-12(r5)
          sw -380(r14), r1
          % processing: tmpVar108 := tmpVar109
          lw r1,-380(r14)
          sw -376(r14), r1
          % processing: tmpVar107 := tmpVar108
          lw r1,-376(r14)
          sw -372(r14), r1
          % processing: tmpVar106 := tmpVar107
          lw r1,-372(r14)
          sw -368(r14), r1
          addi r5, r14, 0
          %processing parameter0: x
          lw r1, -336(r14)
          sw -740(r14), r1
          %processing parameter1: y
          lw r1, -352(r14)
          sw -744(r14), r1
          %processing parameter2: z
          lw r1, -368(r14)
          sw -748(r14), r1
          % processing jump
          sw -812(r14), r14
          addi r14, r14, -736
          jl r15, add0
          lw r1, -740(r14)
          sw -328(r14), r1
          % processing: tmpVar95 := tmpVar96
          lw r1,-328(r14)
          sw -324(r14), r1
          % processing: tmpVar94 := tmpVar95
          lw r1,-324(r14)
          sw -320(r14), r1
          % processing: tmpVar93 := tmpVar94
          lw r1,-320(r14)
          sw -316(r14), r1
          % processing assignment statement
          addi r5, r14, 0
          lw r6,-316(r5)
          sw -12(r5), r6
          % processing line84
          % processing write statement
          % processing line84
          % processing line84
          % processing line84
          % processing line84
          addi r5, r14, 0
          % processing: tmpVar113 := z
          lw r1,-12(r5)
          sw -396(r14), r1
          % processing: tmpVar112 := tmpVar113
          lw r1,-396(r14)
          sw -392(r14), r1
          % processing: tmpVar111 := tmpVar112
          lw r1,-392(r14)
          sw -388(r14), r1
          % processing: tmpVar110 := tmpVar111
          lw r1,-388(r14)
          sw -384(r14), r1
          lw r1, -384(r14)
          addi r14, r14, -736
          sw -8(r14), r1
          addi r1, r0, buf
          sw -12(r14), r1
          jl r15, intstr
          sw -8(r14),r13
          jl r15, putstr
          subi r14, r14, -736
          addi r1, r0, charLineSep
          addi r14, r14, -736
          sw -8(r14), r1
          jl r15, putstr
          subi r14, r14, -736
          % processing line85
          % processing line85
          % processing line85
          % processing line85
          % processing line85
          addi r5, r14, 0
          % processing: tmpVar117 := x
          lw r1,-4(r5)
          sw -412(r14), r1
          % processing: tmpVar116 := tmpVar117
          lw r1,-412(r14)
          sw -408(r14), r1
          % processing: tmpVar115 := tmpVar116
          lw r1,-408(r14)
          sw -404(r14), r1
          % processing line85
          % processing line85
          % processing line85
          addi r5, r14, 0
          % processing: tmpVar121 := y
          lw r1,-8(r5)
          sw -428(r14), r1
          % processing: tmpVar120 := tmpVar121
          lw r1,-428(r14)
          sw -424(r14), r1
          % processing line85
          % processing line85
          % processing: tmpVar124 := 10
          addi r1,r0,10
          sw -440(r14),r1
          % processing: tmpVar123 := tmpVar124
          lw r1,-440(r14)
          sw -436(r14), r1
          % processing: tmpVar119 := tmpVar120
          lw r1,-424(r14)
          sw -420(r14), r1
          % processing: tmpVar119 := tmpVar119 + tmpVar123
          lw r1,-420(r14)
          lw r2,-436(r14)
          add r1,r1,r2
          sw -420(r14),r1
          % processing: tmpVar114 := tmpVar115
          lw r1,-404(r14)
          sw -400(r14), r1
          % processing: tmpVar114 := tmpVar114 gt tmpVar119
          lw r1,-400(r14)
          lw r2,-420(r14)
          cgt r1,r1,r2
          sw -400(r14),r1
          lw r1, -400(r14)
          bz r1, elseStatement1
          % processing line86
          % processing write statement
          % processing line86
          % processing line86
          % processing line86
          % processing line86
          addi r6, r14, 0
          % processing: tmpVar128 := x
          lw r2,-4(r6)
          sw -456(r14), r2
          % processing: tmpVar127 := tmpVar128
          lw r2,-456(r14)
          sw -452(r14), r2
          % processing line86
          % processing line86
          % processing: tmpVar131 := 10
          addi r2,r0,10
          sw -468(r14),r2
          % processing: tmpVar130 := tmpVar131
          lw r2,-468(r14)
          sw -464(r14), r2
          % processing: tmpVar126 := tmpVar127
          lw r2,-452(r14)
          sw -448(r14), r2
          % processing: tmpVar126 := tmpVar126 + tmpVar130
          lw r2,-448(r14)
          lw r3,-464(r14)
          add r2,r2,r3
          sw -448(r14),r2
          % processing: tmpVar125 := tmpVar126
          lw r2,-448(r14)
          sw -444(r14), r2
          lw r2, -444(r14)
          addi r14, r14, -736
          sw -8(r14), r2
          addi r2, r0, buf
          sw -12(r14), r2
          jl r15, intstr
          sw -8(r14),r13
          jl r15, putstr
          subi r14, r14, -736
          addi r2, r0, charLineSep
          addi r14, r14, -736
          sw -8(r14), r2
          jl r15, putstr
          subi r14, r14, -736
          j endif1
elseStatement1
          % processing line88
          % processing write statement
          % processing line88
          % processing line88
          % processing line88
          % processing line88
          addi r6, r14, 0
          % processing: tmpVar135 := x
          lw r2,-4(r6)
          sw -484(r14), r2
          % processing: tmpVar134 := tmpVar135
          lw r2,-484(r14)
          sw -480(r14), r2
          % processing line88
          % processing line88
          % processing: tmpVar138 := 1
          addi r2,r0,1
          sw -496(r14),r2
          % processing: tmpVar137 := tmpVar138
          lw r2,-496(r14)
          sw -492(r14), r2
          % processing: tmpVar133 := tmpVar134
          lw r2,-480(r14)
          sw -476(r14), r2
          % processing: tmpVar133 := tmpVar133 + tmpVar137
          lw r2,-476(r14)
          lw r3,-492(r14)
          add r2,r2,r3
          sw -476(r14),r2
          % processing: tmpVar132 := tmpVar133
          lw r2,-476(r14)
          sw -472(r14), r2
          lw r2, -472(r14)
          addi r14, r14, -736
          sw -8(r14), r2
          addi r2, r0, buf
          sw -12(r14), r2
          jl r15, intstr
          sw -8(r14),r13
          jl r15, putstr
          subi r14, r14, -736
          addi r2, r0, charLineSep
          addi r14, r14, -736
          sw -8(r14), r2
          jl r15, putstr
          subi r14, r14, -736
endif1    
          % processing line90
          % processing line90
          % processing line90
          % processing line90
          % processing: tmpVar142 := 0
          addi r1,r0,0
          sw -512(r14),r1
          % processing: tmpVar141 := tmpVar142
          lw r1,-512(r14)
          sw -508(r14), r1
          % processing: tmpVar140 := tmpVar141
          lw r1,-508(r14)
          sw -504(r14), r1
          % processing: tmpVar139 := tmpVar140
          lw r1,-504(r14)
          sw -500(r14), r1
          % processing assignment statement
          addi r5, r14, 0
          lw r6,-500(r5)
          sw -12(r5), r6
          % processing line92
gowhile2  
          % processing line92
          % processing line92
          % processing line92
          % processing line92
          addi r6, r14, 0
          % processing: tmpVar146 := z
          lw r2,-12(r6)
          sw -528(r14), r2
          % processing: tmpVar145 := tmpVar146
          lw r2,-528(r14)
          sw -524(r14), r2
          % processing: tmpVar144 := tmpVar145
          lw r2,-524(r14)
          sw -520(r14), r2
          % processing line92
          % processing line92
          % processing line92
          % processing: tmpVar150 := 6
          addi r2,r0,6
          sw -544(r14),r2
          % processing: tmpVar149 := tmpVar150
          lw r2,-544(r14)
          sw -540(r14), r2
          % processing: tmpVar148 := tmpVar149
          lw r2,-540(r14)
          sw -536(r14), r2
          % processing: tmpVar143 := tmpVar144
          lw r2,-520(r14)
          sw -516(r14), r2
          % processing: tmpVar143 := tmpVar143 leq tmpVar148
          lw r2,-516(r14)
          lw r3,-536(r14)
          cle r2,r2,r3
          sw -516(r14),r2
          lw r1, -516(r14)
          bz r1, endwhile2
          % processing line93
          % processing write statement
          % processing line93
          % processing line93
          % processing line93
          % processing line93
          % processing line93
          % processing line93
          % processing line93
          addi r6, r14, 0
          % processing: tmpVar157 := z
          lw r2,-12(r6)
          sw -572(r14), r2
          % processing: tmpVar156 := tmpVar157
          lw r2,-572(r14)
          sw -568(r14), r2
          % processing: tmpVar155 := tmpVar156
          lw r2,-568(r14)
          sw -564(r14), r2
          addi r6, r14, 0
          % processing: tmpVar154 := arr
          addi r2, r0, 0 
          addi r3, r0, 4 
          % processing: dimension 0
          lw r4, -564(r6)
          add r2, r2, r4
          mul r2, r2, r3 
          muli r3, r3, 7 
          muli r2, r2, -1
          lw r5, -16(r6)
          add r2, r5, r2
          lw r4, 0(r2)
          sw -560(r14), r4
          % processing: tmpVar153 := tmpVar154
          lw r2,-560(r14)
          sw -556(r14), r2
          % processing: tmpVar152 := tmpVar153
          lw r2,-556(r14)
          sw -552(r14), r2
          % processing: tmpVar151 := tmpVar152
          lw r2,-552(r14)
          sw -548(r14), r2
          lw r2, -548(r14)
          addi r14, r14, -736
          sw -8(r14), r2
          addi r2, r0, buf
          sw -12(r14), r2
          jl r15, intstr
          sw -8(r14),r13
          jl r15, putstr
          subi r14, r14, -736
          addi r2, r0, charLineSep
          addi r14, r14, -736
          sw -8(r14), r2
          jl r15, putstr
          subi r14, r14, -736
          % processing line94
          % processing line94
          % processing line94
          % processing line94
          addi r6, r14, 0
          % processing: tmpVar161 := z
          lw r2,-12(r6)
          sw -588(r14), r2
          % processing: tmpVar160 := tmpVar161
          lw r2,-588(r14)
          sw -584(r14), r2
          % processing line94
          % processing line94
          % processing: tmpVar164 := 1
          addi r2,r0,1
          sw -600(r14),r2
          % processing: tmpVar163 := tmpVar164
          lw r2,-600(r14)
          sw -596(r14), r2
          % processing: tmpVar159 := tmpVar160
          lw r2,-584(r14)
          sw -580(r14), r2
          % processing: tmpVar159 := tmpVar159 + tmpVar163
          lw r2,-580(r14)
          lw r3,-596(r14)
          add r2,r2,r3
          sw -580(r14),r2
          % processing: tmpVar158 := tmpVar159
          lw r2,-580(r14)
          sw -576(r14), r2
          % processing assignment statement
          addi r6, r14, 0
          lw r7,-576(r6)
          sw -12(r6), r7
          j gowhile2
endwhile2 
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
