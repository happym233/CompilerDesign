add0
          % processing line50
          % processing line50
          % processing line50
          % processing line50
          addi r5, r14, 0
          % processing: tmpVar3 := x
          lw r1,-4(r5)
          sw -24(r5), r1
          % processing: tmpVar2 := tmpVar3
          lw r1,-24(r14)
          sw -20(r14), r1
          % processing line50
          % processing line50
          addi r5, r14, 0
          % processing: tmpVar6 := y
          lw r1,-8(r5)
          sw -36(r5), r1
          % processing: tmpVar5 := tmpVar6
          lw r1,-36(r14)
          sw -32(r14), r1
          % processing: tmpVar1 := tmpVar2
          lw r1,-20(r14)
          sw -16(r14), r1
          % processing: tmpVar1 := tmpVar1 + tmpVar5
          lw r1,-16(r14)
          lw r2,-32(r14)
          add r1,r1,r2
          sw -16(r14),r1
          % processing: tmpVar0 := tmpVar1
          lw r1,-16(r14)
          sw -12(r14), r1
          % processing line50
          lw r1, -12(r14)
          sw -4(r14), r1
          jr r15
entry
          addi r14, r0, topaddr
          % processing pointer
          addi r1, r0, -572
          add r1, r1, r14
          sw -16(r14), r1
          % processing pointer
          addi r1, r0, -600
          add r1, r1, r14
          sw -20(r14), r1
          % processing pointer
          addi r1, r0, -612
          add r1, r1, r14
          sw -24(r14), r1
          % processing line67
          % processing line67
          % processing line67
          % processing: tmpVar9 := 0
          addi r1,r0,0
          sw -36(r14),r1
          % processing: tmpVar8 := tmpVar9
          lw r1,-36(r14)
          sw -32(r14), r1
          % processing: tmpVar7 := tmpVar8
          lw r1,-32(r14)
          sw -28(r14), r1
          % processing line67
          % processing line67
          % processing line67
          % processing line67
          % processing: tmpVar13 := 64
          addi r1,r0,64
          sw -52(r14),r1
          % processing: tmpVar12 := tmpVar13
          lw r1,-52(r14)
          sw -48(r14), r1
          % processing: tmpVar11 := tmpVar12
          lw r1,-48(r14)
          sw -44(r14), r1
          % processing: tmpVar10 := tmpVar11
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
          % processing line68
          % processing line68
          % processing line68
          % processing: tmpVar16 := 1
          addi r1,r0,1
          sw -64(r14),r1
          % processing: tmpVar15 := tmpVar16
          lw r1,-64(r14)
          sw -60(r14), r1
          % processing: tmpVar14 := tmpVar15
          lw r1,-60(r14)
          sw -56(r14), r1
          % processing line68
          % processing line68
          % processing line68
          % processing line68
          % processing: tmpVar20 := 34
          addi r1,r0,34
          sw -80(r14),r1
          % processing: tmpVar19 := tmpVar20
          lw r1,-80(r14)
          sw -76(r14), r1
          % processing: tmpVar18 := tmpVar19
          lw r1,-76(r14)
          sw -72(r14), r1
          % processing: tmpVar17 := tmpVar18
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
          % processing line69
          % processing line69
          % processing line69
          % processing: tmpVar23 := 2
          addi r1,r0,2
          sw -92(r14),r1
          % processing: tmpVar22 := tmpVar23
          lw r1,-92(r14)
          sw -88(r14), r1
          % processing: tmpVar21 := tmpVar22
          lw r1,-88(r14)
          sw -84(r14), r1
          % processing line69
          % processing line69
          % processing line69
          % processing line69
          % processing: tmpVar27 := 25
          addi r1,r0,25
          sw -108(r14),r1
          % processing: tmpVar26 := tmpVar27
          lw r1,-108(r14)
          sw -104(r14), r1
          % processing: tmpVar25 := tmpVar26
          lw r1,-104(r14)
          sw -100(r14), r1
          % processing: tmpVar24 := tmpVar25
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
          % processing line70
          % processing line70
          % processing line70
          % processing: tmpVar30 := 3
          addi r1,r0,3
          sw -120(r14),r1
          % processing: tmpVar29 := tmpVar30
          lw r1,-120(r14)
          sw -116(r14), r1
          % processing: tmpVar28 := tmpVar29
          lw r1,-116(r14)
          sw -112(r14), r1
          % processing line70
          % processing line70
          % processing line70
          % processing line70
          % processing: tmpVar34 := 12
          addi r1,r0,12
          sw -136(r14),r1
          % processing: tmpVar33 := tmpVar34
          lw r1,-136(r14)
          sw -132(r14), r1
          % processing: tmpVar32 := tmpVar33
          lw r1,-132(r14)
          sw -128(r14), r1
          % processing: tmpVar31 := tmpVar32
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
          % processing line71
          % processing line71
          % processing line71
          % processing: tmpVar37 := 4
          addi r1,r0,4
          sw -148(r14),r1
          % processing: tmpVar36 := tmpVar37
          lw r1,-148(r14)
          sw -144(r14), r1
          % processing: tmpVar35 := tmpVar36
          lw r1,-144(r14)
          sw -140(r14), r1
          % processing line71
          % processing line71
          % processing line71
          % processing line71
          % processing: tmpVar41 := 22
          addi r1,r0,22
          sw -164(r14),r1
          % processing: tmpVar40 := tmpVar41
          lw r1,-164(r14)
          sw -160(r14), r1
          % processing: tmpVar39 := tmpVar40
          lw r1,-160(r14)
          sw -156(r14), r1
          % processing: tmpVar38 := tmpVar39
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
          % processing line72
          % processing line72
          % processing line72
          % processing: tmpVar44 := 5
          addi r1,r0,5
          sw -176(r14),r1
          % processing: tmpVar43 := tmpVar44
          lw r1,-176(r14)
          sw -172(r14), r1
          % processing: tmpVar42 := tmpVar43
          lw r1,-172(r14)
          sw -168(r14), r1
          % processing line72
          % processing line72
          % processing line72
          % processing line72
          % processing: tmpVar48 := 11
          addi r1,r0,11
          sw -192(r14),r1
          % processing: tmpVar47 := tmpVar48
          lw r1,-192(r14)
          sw -188(r14), r1
          % processing: tmpVar46 := tmpVar47
          lw r1,-188(r14)
          sw -184(r14), r1
          % processing: tmpVar45 := tmpVar46
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
          % processing line73
          % processing line73
          % processing line73
          % processing: tmpVar51 := 6
          addi r1,r0,6
          sw -204(r14),r1
          % processing: tmpVar50 := tmpVar51
          lw r1,-204(r14)
          sw -200(r14), r1
          % processing: tmpVar49 := tmpVar50
          lw r1,-200(r14)
          sw -196(r14), r1
          % processing line73
          % processing line73
          % processing line73
          % processing line73
          % processing: tmpVar55 := 90
          addi r1,r0,90
          sw -220(r14),r1
          % processing: tmpVar54 := tmpVar55
          lw r1,-220(r14)
          sw -216(r14), r1
          % processing: tmpVar53 := tmpVar54
          lw r1,-216(r14)
          sw -212(r14), r1
          % processing: tmpVar52 := tmpVar53
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
          % processing line74
          % processing line74
          % processing line74
          % processing line74
          % processing: tmpVar59 := 1
          addi r1,r0,1
          sw -236(r14),r1
          % processing: tmpVar58 := tmpVar59
          lw r1,-236(r14)
          sw -232(r14), r1
          % processing line74
          % processing line74
          % processing: tmpVar62 := 2
          addi r1,r0,2
          sw -248(r14),r1
          % processing line74
          % processing: tmpVar64 := 3
          addi r1,r0,3
          sw -256(r14),r1
          % processing: tmpVar61 := tmpVar62
          lw r1,-248(r14)
          sw -244(r14), r1
          % processing: tmpVar61 := tmpVar61 * tmpVar64
          lw r1,-244(r14)
          lw r2,-256(r14)
          mul r1,r1,r2
          sw -244(r14),r1
          % processing: tmpVar57 := tmpVar58
          lw r1,-232(r14)
          sw -228(r14), r1
          % processing: tmpVar57 := tmpVar57 + tmpVar61
          lw r1,-228(r14)
          lw r2,-244(r14)
          add r1,r1,r2
          sw -228(r14),r1
          % processing: tmpVar56 := tmpVar57
          lw r1,-228(r14)
          sw -224(r14), r1
          % processing assignment statement
          addi r5, r14, 0
          lw r6,-224(r5)
          sw -8(r5), r6
          % processing line75
          % processing write statement
          % processing line75
          % processing line75
          % processing line75
          % processing line75
          % processing line75
          % processing line75
          % processing line75
          addi r5, r14, 0
          % processing: tmpVar71 := y
          lw r1,-8(r5)
          sw -284(r5), r1
          % processing: tmpVar70 := tmpVar71
          lw r1,-284(r14)
          sw -280(r14), r1
          % processing line75
          % processing line75
          % processing: tmpVar74 := 4
          addi r1,r0,4
          sw -296(r14),r1
          % processing: tmpVar73 := tmpVar74
          lw r1,-296(r14)
          sw -292(r14), r1
          % processing: tmpVar69 := tmpVar70
          lw r1,-280(r14)
          sw -276(r14), r1
          % processing: tmpVar69 := tmpVar69 - tmpVar73
          lw r1,-276(r14)
          lw r2,-292(r14)
          sub r1,r1,r2
          sw -276(r14),r1
          addi r5, r14, 0
          % processing: tmpVar68 := arr
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
          % processing: tmpVar67 := tmpVar68
          lw r1,-272(r14)
          sw -268(r14), r1
          % processing: tmpVar66 := tmpVar67
          lw r1,-268(r14)
          sw -264(r14), r1
          % processing: tmpVar65 := tmpVar66
          lw r1,-264(r14)
          sw -260(r14), r1
          lw r1, -260(r14)
          addi r14, r14, -696
          sw -8(r14), r1
          addi r1, r0, buf
          sw -12(r14), r1
          jl r15, intstr
          sw -8(r14),r13
          jl r15, putstr
          subi r14, r14, -696
          addi r1, r0, charLineSep
          addi r14, r14, -696
          sw -8(r14), r1
          jl r15, putstr
          subi r14, r14, -696
          % processing line76
          % processing read statement
          addi r5, r14, 0
          addi r1, r0, buf
          addi r14, r14, -696
          sw -8(r14), r1
          jl r15,getstr
          jl r15,strint
          addi r6, r13, 0
          subi r14, r14, -696
          sw -4(r5), r6
          % processing line77
          % processing line77
          % processing line77
          % processing line77
          % processing line77
          % processing line77
          % processing line77
          % processing line77
          addi r5, r14, 0
          % processing: tmpVar83 := x
          lw r1,-4(r5)
          sw -332(r5), r1
          % processing: tmpVar82 := tmpVar83
          lw r1,-332(r14)
          sw -328(r14), r1
          % processing: tmpVar81 := tmpVar82
          lw r1,-328(r14)
          sw -324(r14), r1
          % processing: tmpVar80 := tmpVar81
          lw r1,-324(r14)
          sw -320(r14), r1
          % processing line77
          % processing line77
          % processing line77
          % processing line77
          addi r5, r14, 0
          % processing: tmpVar87 := y
          lw r1,-8(r5)
          sw -348(r5), r1
          % processing: tmpVar86 := tmpVar87
          lw r1,-348(r14)
          sw -344(r14), r1
          % processing: tmpVar85 := tmpVar86
          lw r1,-344(r14)
          sw -340(r14), r1
          % processing: tmpVar84 := tmpVar85
          lw r1,-340(r14)
          sw -336(r14), r1
          %processing parameter0: x
          lw r1, -320(r14)
          sw -700(r14), r1
          %processing parameter1: y
          lw r1, -336(r14)
          sw -704(r14), r1
          addi r14, r14, -696
          jl r15, add0
          lw r1, -4(r14)
          subi r14, r14, -696
          sw -312(r14), r1
          % processing: tmpVar77 := tmpVar78
          lw r1,-312(r14)
          sw -308(r14), r1
          % processing: tmpVar76 := tmpVar77
          lw r1,-308(r14)
          sw -304(r14), r1
          % processing: tmpVar75 := tmpVar76
          lw r1,-304(r14)
          sw -300(r14), r1
          % processing assignment statement
          addi r5, r14, 0
          lw r6,-300(r5)
          sw -12(r5), r6
          % processing line78
          % processing write statement
          % processing line78
          % processing line78
          % processing line78
          % processing line78
          addi r5, r14, 0
          % processing: tmpVar91 := z
          lw r1,-12(r5)
          sw -364(r5), r1
          % processing: tmpVar90 := tmpVar91
          lw r1,-364(r14)
          sw -360(r14), r1
          % processing: tmpVar89 := tmpVar90
          lw r1,-360(r14)
          sw -356(r14), r1
          % processing: tmpVar88 := tmpVar89
          lw r1,-356(r14)
          sw -352(r14), r1
          lw r1, -352(r14)
          addi r14, r14, -696
          sw -8(r14), r1
          addi r1, r0, buf
          sw -12(r14), r1
          jl r15, intstr
          sw -8(r14),r13
          jl r15, putstr
          subi r14, r14, -696
          addi r1, r0, charLineSep
          addi r14, r14, -696
          sw -8(r14), r1
          jl r15, putstr
          subi r14, r14, -696
          % processing line79
          % processing line79
          % processing line79
          % processing line79
          % processing line79
          addi r5, r14, 0
          % processing: tmpVar95 := x
          lw r1,-4(r5)
          sw -380(r5), r1
          % processing: tmpVar94 := tmpVar95
          lw r1,-380(r14)
          sw -376(r14), r1
          % processing: tmpVar93 := tmpVar94
          lw r1,-376(r14)
          sw -372(r14), r1
          % processing line79
          % processing line79
          % processing line79
          addi r5, r14, 0
          % processing: tmpVar99 := y
          lw r1,-8(r5)
          sw -396(r5), r1
          % processing: tmpVar98 := tmpVar99
          lw r1,-396(r14)
          sw -392(r14), r1
          % processing line79
          % processing line79
          % processing: tmpVar102 := 10
          addi r1,r0,10
          sw -408(r14),r1
          % processing: tmpVar101 := tmpVar102
          lw r1,-408(r14)
          sw -404(r14), r1
          % processing: tmpVar97 := tmpVar98
          lw r1,-392(r14)
          sw -388(r14), r1
          % processing: tmpVar97 := tmpVar97 + tmpVar101
          lw r1,-388(r14)
          lw r2,-404(r14)
          add r1,r1,r2
          sw -388(r14),r1
          % processing: tmpVar92 := tmpVar93
          lw r1,-372(r14)
          sw -368(r14), r1
          % processing: tmpVar92 := tmpVar92 gt tmpVar97
          lw r1,-368(r14)
          lw r2,-388(r14)
          cgt r1,r1,r2
          sw -368(r14),r1
          lw r1, -368(r14)
          bz r1, elseStatement1
          % processing line80
          % processing write statement
          % processing line80
          % processing line80
          % processing line80
          % processing line80
          addi r6, r14, 0
          % processing: tmpVar106 := x
          lw r2,-4(r6)
          sw -424(r6), r2
          % processing: tmpVar105 := tmpVar106
          lw r2,-424(r14)
          sw -420(r14), r2
          % processing line80
          % processing line80
          % processing: tmpVar109 := 10
          addi r2,r0,10
          sw -436(r14),r2
          % processing: tmpVar108 := tmpVar109
          lw r2,-436(r14)
          sw -432(r14), r2
          % processing: tmpVar104 := tmpVar105
          lw r2,-420(r14)
          sw -416(r14), r2
          % processing: tmpVar104 := tmpVar104 + tmpVar108
          lw r2,-416(r14)
          lw r3,-432(r14)
          add r2,r2,r3
          sw -416(r14),r2
          % processing: tmpVar103 := tmpVar104
          lw r2,-416(r14)
          sw -412(r14), r2
          lw r2, -412(r14)
          addi r14, r14, -696
          sw -8(r14), r2
          addi r2, r0, buf
          sw -12(r14), r2
          jl r15, intstr
          sw -8(r14),r13
          jl r15, putstr
          subi r14, r14, -696
          addi r2, r0, charLineSep
          addi r14, r14, -696
          sw -8(r14), r2
          jl r15, putstr
          subi r14, r14, -696
          j endif1
elseStatement1
          % processing line82
          % processing write statement
          % processing line82
          % processing line82
          % processing line82
          % processing line82
          addi r6, r14, 0
          % processing: tmpVar113 := x
          lw r2,-4(r6)
          sw -452(r6), r2
          % processing: tmpVar112 := tmpVar113
          lw r2,-452(r14)
          sw -448(r14), r2
          % processing line82
          % processing line82
          % processing: tmpVar116 := 1
          addi r2,r0,1
          sw -464(r14),r2
          % processing: tmpVar115 := tmpVar116
          lw r2,-464(r14)
          sw -460(r14), r2
          % processing: tmpVar111 := tmpVar112
          lw r2,-448(r14)
          sw -444(r14), r2
          % processing: tmpVar111 := tmpVar111 + tmpVar115
          lw r2,-444(r14)
          lw r3,-460(r14)
          add r2,r2,r3
          sw -444(r14),r2
          % processing: tmpVar110 := tmpVar111
          lw r2,-444(r14)
          sw -440(r14), r2
          lw r2, -440(r14)
          addi r14, r14, -696
          sw -8(r14), r2
          addi r2, r0, buf
          sw -12(r14), r2
          jl r15, intstr
          sw -8(r14),r13
          jl r15, putstr
          subi r14, r14, -696
          addi r2, r0, charLineSep
          addi r14, r14, -696
          sw -8(r14), r2
          jl r15, putstr
          subi r14, r14, -696
endif1    
          % processing line84
          % processing line84
          % processing line84
          % processing line84
          % processing: tmpVar120 := 0
          addi r1,r0,0
          sw -480(r14),r1
          % processing: tmpVar119 := tmpVar120
          lw r1,-480(r14)
          sw -476(r14), r1
          % processing: tmpVar118 := tmpVar119
          lw r1,-476(r14)
          sw -472(r14), r1
          % processing: tmpVar117 := tmpVar118
          lw r1,-472(r14)
          sw -468(r14), r1
          % processing assignment statement
          addi r5, r14, 0
          lw r6,-468(r5)
          sw -12(r5), r6
          % processing line86
gowhile2  
          % processing line86
          % processing line86
          % processing line86
          % processing line86
          addi r6, r14, 0
          % processing: tmpVar124 := z
          lw r2,-12(r6)
          sw -496(r6), r2
          % processing: tmpVar123 := tmpVar124
          lw r2,-496(r14)
          sw -492(r14), r2
          % processing: tmpVar122 := tmpVar123
          lw r2,-492(r14)
          sw -488(r14), r2
          % processing line86
          % processing line86
          % processing line86
          % processing: tmpVar128 := 6
          addi r2,r0,6
          sw -512(r14),r2
          % processing: tmpVar127 := tmpVar128
          lw r2,-512(r14)
          sw -508(r14), r2
          % processing: tmpVar126 := tmpVar127
          lw r2,-508(r14)
          sw -504(r14), r2
          % processing: tmpVar121 := tmpVar122
          lw r2,-488(r14)
          sw -484(r14), r2
          % processing: tmpVar121 := tmpVar121 leq tmpVar126
          lw r2,-484(r14)
          lw r3,-504(r14)
          cle r2,r2,r3
          sw -484(r14),r2
          lw r1, -484(r14)
          bz r1, endwhile2
          % processing line87
          % processing write statement
          % processing line87
          % processing line87
          % processing line87
          % processing line87
          % processing line87
          % processing line87
          % processing line87
          addi r6, r14, 0
          % processing: tmpVar135 := z
          lw r2,-12(r6)
          sw -540(r6), r2
          % processing: tmpVar134 := tmpVar135
          lw r2,-540(r14)
          sw -536(r14), r2
          % processing: tmpVar133 := tmpVar134
          lw r2,-536(r14)
          sw -532(r14), r2
          addi r6, r14, 0
          % processing: tmpVar132 := arr
          addi r2, r0, 0 
          addi r3, r0, 4 
          % processing: dimension 0
          lw r4, -532(r6)
          add r2, r2, r4
          mul r2, r2, r3 
          muli r3, r3, 7 
          muli r2, r2, -1
          lw r5, -16(r6)
          add r2, r5, r2
          lw r4, 0(r2)
          sw -528(r14), r4
          % processing: tmpVar131 := tmpVar132
          lw r2,-528(r14)
          sw -524(r14), r2
          % processing: tmpVar130 := tmpVar131
          lw r2,-524(r14)
          sw -520(r14), r2
          % processing: tmpVar129 := tmpVar130
          lw r2,-520(r14)
          sw -516(r14), r2
          lw r2, -516(r14)
          addi r14, r14, -696
          sw -8(r14), r2
          addi r2, r0, buf
          sw -12(r14), r2
          jl r15, intstr
          sw -8(r14),r13
          jl r15, putstr
          subi r14, r14, -696
          addi r2, r0, charLineSep
          addi r14, r14, -696
          sw -8(r14), r2
          jl r15, putstr
          subi r14, r14, -696
          % processing line88
          % processing line88
          % processing line88
          % processing line88
          addi r6, r14, 0
          % processing: tmpVar139 := z
          lw r2,-12(r6)
          sw -556(r6), r2
          % processing: tmpVar138 := tmpVar139
          lw r2,-556(r14)
          sw -552(r14), r2
          % processing line88
          % processing line88
          % processing: tmpVar142 := 1
          addi r2,r0,1
          sw -568(r14),r2
          % processing: tmpVar141 := tmpVar142
          lw r2,-568(r14)
          sw -564(r14), r2
          % processing: tmpVar137 := tmpVar138
          lw r2,-552(r14)
          sw -548(r14), r2
          % processing: tmpVar137 := tmpVar137 + tmpVar141
          lw r2,-548(r14)
          lw r3,-564(r14)
          add r2,r2,r3
          sw -548(r14),r2
          % processing: tmpVar136 := tmpVar137
          lw r2,-548(r14)
          sw -544(r14), r2
          % processing assignment statement
          addi r6, r14, 0
          lw r7,-544(r6)
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
