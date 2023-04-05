evaluate0
          % processing line71
          lw r1, -16(r14)
          lw r2, -20(r14)
          sw -4(r14), r1
          sw -8(r14), r2
          % processing line71
          % processing write statement
          % processing line71
          % processing line71
          % processing line71
          % processing line71
          % processing: tmpVar3 := 0.0
          addi r1,r0,0
          sw -40(r14),r1
          % processing: tmpVar3 := 0.0
          addi r1,r0,0
          sw -44(r14),r1
          % processing: tmpVar2 := tmpVar3
          lw r1,-40(r14)
          lw r2,-44(r14)
          sw -32(r14), r1
          sw -36(r14), r2
          % processing: tmpVar1 := tmpVar2
          lw r1,-32(r14)
          lw r2,-36(r14)
          sw -24(r14), r1
          sw -28(r14), r2
          % processing: tmpVar0 := tmpVar1
          lw r1,-24(r14)
          lw r2,-28(r14)
          sw -16(r14), r1
          sw -20(r14), r2
          lw r1, -16(r14)
          addi r14, r14, -48
          sw -8(r14), r1
          addi r1, r0, buf
          sw -12(r14), r1
          jl r15, intstr
          sw -8(r14),r13
          jl r15, putstr
          subi r14, r14, -48
          addi r1, r0, charE
          addi r14, r14, -48
          sw -8(r14), r1
          jl r15, putstr
          subi r14, r14, -48
          lw r1, -20(r14)
          addi r14, r14, -48
          sw -8(r14), r1
          addi r1, r0, buf
          sw -12(r14), r1
          jl r15, intstr
          sw -8(r14),r13
          jl r15, putstr
          subi r14, r14, -48
          addi r1, r0, charLineSep
          addi r14, r14, -48
          sw -8(r14), r1
          jl r15, putstr
          subi r14, r14, -48
jr r15
evaluate1
          % processing line78
          % processing line78
          % processing line78
          % processing line78
          % processing line78
          addi r5, r14, 0
          % processing: tmpVar7 := a
          lw r1,-4(r5)
          addi r5, r1, 0 
          addi r5, r14, 0
          % processing: tmpVar7 := a
          lw r1,0(r5)
          lw r2,-4(r5)
          sw -48(r5), r1
          sw -52(r5), r2
          % processing: tmpVar6 := tmpVar7
          lw r1,-48(r14)
          lw r2,-52(r14)
          sw -40(r14), r1
          sw -44(r14), r2
          % processing: tmpVar5 := tmpVar6
          lw r1,-40(r14)
          lw r2,-44(r14)
          sw -32(r14), r1
          sw -36(r14), r2
          % processing: tmpVar4 := tmpVar5
          lw r1,-32(r14)
          lw r2,-36(r14)
          sw -24(r14), r1
          sw -28(r14), r2
          % processing assignment statement
          addi r5, r14, 0
          lw r6,-24(r5)
          lw r7,-28(r5)
          sw -16(r5), r6
          sw -20(r5), r7
          % processing line79
          % processing line79
          % processing line79
          % processing line79
          % processing line79
          addi r5, r14, 0
          % processing: tmpVar11 := result
          lw r1,-16(r5)
          lw r2,-20(r5)
          sw -80(r5), r1
          sw -84(r5), r2
          % processing line79
          addi r5, r14, 0
          % processing: tmpVar13 := x
          lw r1,-8(r5)
          lw r2,-12(r5)
          sw -96(r5), r1
          sw -100(r5), r2
          % processing: tmpVar10 := tmpVar11
          lw r1,-80(r14)
          lw r2,-84(r14)
          sw -72(r14), r1
          sw -76(r14), r2
          lw r1,-72(r14)
          lw r2,-76(r14)
          lw r3,-96(r14)
          lw r4,-100(r14)
          sw -236(r14), r1
          sw -240(r14), r2
          sw -244(r14), r3
          sw -248(r14), r4
          addi r14, r14, -232
          jl r15, floatMul
          lw r1, -4(r14)
          lw r2, -8(r14)
          subi r14, r14, -232
          sw -72(r14), r1
          sw -76(r14), r2
          % processing line79
          % processing line79
          addi r5, r14, 0
          % processing: tmpVar16 := b
          lw r1,-4(r5)
          addi r5, r1, 0 
          addi r5, r14, 0
          % processing: tmpVar16 := b
          lw r1,-8(r5)
          lw r2,-12(r5)
          sw -120(r5), r1
          sw -124(r5), r2
          % processing: tmpVar15 := tmpVar16
          lw r1,-120(r14)
          lw r2,-124(r14)
          sw -112(r14), r1
          sw -116(r14), r2
          % processing: tmpVar9 := tmpVar10
          lw r1,-72(r14)
          lw r2,-76(r14)
          sw -64(r14), r1
          sw -68(r14), r2
          lw r1,-64(r14)
          lw r2,-68(r14)
          lw r3,-112(r14)
          lw r4,-116(r14)
          sw -236(r14), r1
          sw -240(r14), r2
          sw -244(r14), r3
          sw -248(r14), r4
          addi r14, r14, -232
          jl r15, floatAdd
          lw r1, -4(r14)
          lw r2, -8(r14)
          subi r14, r14, -232
          sw -64(r14), r1
          sw -68(r14), r2
          % processing: tmpVar8 := tmpVar9
          lw r1,-64(r14)
          lw r2,-68(r14)
          sw -56(r14), r1
          sw -60(r14), r2
          % processing assignment statement
          addi r5, r14, 0
          lw r6,-56(r5)
          lw r7,-60(r5)
          sw -16(r5), r6
          sw -20(r5), r7
          % processing line80
          % processing line80
          % processing line80
          % processing line80
          % processing line80
          addi r5, r14, 0
          % processing: tmpVar20 := result
          lw r1,-16(r5)
          lw r2,-20(r5)
          sw -152(r5), r1
          sw -156(r5), r2
          % processing line80
          addi r5, r14, 0
          % processing: tmpVar22 := x
          lw r1,-8(r5)
          lw r2,-12(r5)
          sw -168(r5), r1
          sw -172(r5), r2
          % processing: tmpVar19 := tmpVar20
          lw r1,-152(r14)
          lw r2,-156(r14)
          sw -144(r14), r1
          sw -148(r14), r2
          lw r1,-144(r14)
          lw r2,-148(r14)
          lw r3,-168(r14)
          lw r4,-172(r14)
          sw -236(r14), r1
          sw -240(r14), r2
          sw -244(r14), r3
          sw -248(r14), r4
          addi r14, r14, -232
          jl r15, floatMul
          lw r1, -4(r14)
          lw r2, -8(r14)
          subi r14, r14, -232
          sw -144(r14), r1
          sw -148(r14), r2
          % processing line80
          % processing line80
          addi r5, r14, 0
          % processing: tmpVar25 := c
          lw r1,-4(r5)
          addi r5, r1, 0 
          addi r5, r14, 0
          % processing: tmpVar25 := c
          lw r1,-16(r5)
          lw r2,-20(r5)
          sw -192(r5), r1
          sw -196(r5), r2
          % processing: tmpVar24 := tmpVar25
          lw r1,-192(r14)
          lw r2,-196(r14)
          sw -184(r14), r1
          sw -188(r14), r2
          % processing: tmpVar18 := tmpVar19
          lw r1,-144(r14)
          lw r2,-148(r14)
          sw -136(r14), r1
          sw -140(r14), r2
          lw r1,-136(r14)
          lw r2,-140(r14)
          lw r3,-184(r14)
          lw r4,-188(r14)
          sw -236(r14), r1
          sw -240(r14), r2
          sw -244(r14), r3
          sw -248(r14), r4
          addi r14, r14, -232
          jl r15, floatAdd
          lw r1, -4(r14)
          lw r2, -8(r14)
          subi r14, r14, -232
          sw -136(r14), r1
          sw -140(r14), r2
          % processing: tmpVar17 := tmpVar18
          lw r1,-136(r14)
          lw r2,-140(r14)
          sw -128(r14), r1
          sw -132(r14), r2
          % processing assignment statement
          addi r5, r14, 0
          lw r6,-128(r5)
          lw r7,-132(r5)
          sw -16(r5), r6
          sw -20(r5), r7
          % processing line81
          lw r1, -200(r14)
          lw r2, -204(r14)
          sw -4(r14), r1
          sw -8(r14), r2
          % processing line81
          % processing write statement
          % processing line81
          % processing line81
          % processing line81
          % processing line81
          addi r5, r14, 0
          % processing: tmpVar29 := result
          lw r1,-16(r5)
          lw r2,-20(r5)
          sw -224(r5), r1
          sw -228(r5), r2
          % processing: tmpVar28 := tmpVar29
          lw r1,-224(r14)
          lw r2,-228(r14)
          sw -216(r14), r1
          sw -220(r14), r2
          % processing: tmpVar27 := tmpVar28
          lw r1,-216(r14)
          lw r2,-220(r14)
          sw -208(r14), r1
          sw -212(r14), r2
          % processing: tmpVar26 := tmpVar27
          lw r1,-208(r14)
          lw r2,-212(r14)
          sw -200(r14), r1
          sw -204(r14), r2
          lw r1, -200(r14)
          addi r14, r14, -232
          sw -8(r14), r1
          addi r1, r0, buf
          sw -12(r14), r1
          jl r15, intstr
          sw -8(r14),r13
          jl r15, putstr
          subi r14, r14, -232
          addi r1, r0, charE
          addi r14, r14, -232
          sw -8(r14), r1
          jl r15, putstr
          subi r14, r14, -232
          lw r1, -204(r14)
          addi r14, r14, -232
          sw -8(r14), r1
          addi r1, r0, buf
          sw -12(r14), r1
          jl r15, intstr
          sw -8(r14),r13
          jl r15, putstr
          subi r14, r14, -232
          addi r1, r0, charLineSep
          addi r14, r14, -232
          sw -8(r14), r1
          jl r15, putstr
          subi r14, r14, -232
jr r15
constructor2
          % processing line86
          % processing line86
          % processing line86
          % processing line86
          % processing line86
          addi r5, r14, 0
          % processing: tmpVar33 := A
          lw r1,-8(r5)
          lw r2,-12(r5)
          sw -56(r5), r1
          sw -60(r5), r2
          % processing: tmpVar32 := tmpVar33
          lw r1,-56(r14)
          lw r2,-60(r14)
          sw -48(r14), r1
          sw -52(r14), r2
          % processing: tmpVar31 := tmpVar32
          lw r1,-48(r14)
          lw r2,-52(r14)
          sw -40(r14), r1
          sw -44(r14), r2
          % processing: tmpVar30 := tmpVar31
          lw r1,-40(r14)
          lw r2,-44(r14)
          sw -32(r14), r1
          sw -36(r14), r2
          % processing assignment statement
          addi r5, r14, 0
          lw r6,-32(r5)
          lw r7,-36(r5)
          sw -4(r5), r6
          lw r1,-4(r5)
          addi r5, r1, 0 
          addi r1, r0, 0 
          addi r2, r0, 8 
          muli r1, r1, -1
          add r1, r5, r1
          addi r1, r1, 0
          sw  0(r1), r6
          sw -4(r1), r7
          % processing line87
          % processing line87
          % processing line87
          % processing line87
          % processing line87
          addi r5, r14, 0
          % processing: tmpVar37 := B
          lw r1,-16(r5)
          lw r2,-20(r5)
          sw -88(r5), r1
          sw -92(r5), r2
          % processing: tmpVar36 := tmpVar37
          lw r1,-88(r14)
          lw r2,-92(r14)
          sw -80(r14), r1
          sw -84(r14), r2
          % processing: tmpVar35 := tmpVar36
          lw r1,-80(r14)
          lw r2,-84(r14)
          sw -72(r14), r1
          sw -76(r14), r2
          % processing: tmpVar34 := tmpVar35
          lw r1,-72(r14)
          lw r2,-76(r14)
          sw -64(r14), r1
          sw -68(r14), r2
          % processing assignment statement
          addi r5, r14, 0
          lw r6,-64(r5)
          lw r7,-68(r5)
          sw -4(r5), r6
          lw r1,-4(r5)
          addi r5, r1, 0 
          addi r1, r0, 0 
          addi r2, r0, 8 
          muli r1, r1, -1
          add r1, r5, r1
          addi r1, r1, -8
          sw  0(r1), r6
          sw -4(r1), r7
          % processing line88
          % processing line88
          % processing line88
          % processing line88
          % processing line88
          addi r5, r14, 0
          % processing: tmpVar41 := C
          lw r1,-24(r5)
          lw r2,-28(r5)
          sw -120(r5), r1
          sw -124(r5), r2
          % processing: tmpVar40 := tmpVar41
          lw r1,-120(r14)
          lw r2,-124(r14)
          sw -112(r14), r1
          sw -116(r14), r2
          % processing: tmpVar39 := tmpVar40
          lw r1,-112(r14)
          lw r2,-116(r14)
          sw -104(r14), r1
          sw -108(r14), r2
          % processing: tmpVar38 := tmpVar39
          lw r1,-104(r14)
          lw r2,-108(r14)
          sw -96(r14), r1
          sw -100(r14), r2
          % processing assignment statement
          addi r5, r14, 0
          lw r6,-96(r5)
          lw r7,-100(r5)
          sw -4(r5), r6
          lw r1,-4(r5)
          addi r5, r1, 0 
          addi r1, r0, 0 
          addi r2, r0, 8 
          muli r1, r1, -1
          add r1, r5, r1
          addi r1, r1, -16
          sw  0(r1), r6
          sw -4(r1), r7
jr r15
constructor3
          % processing line93
          % processing line93
          % processing line93
          % processing line93
          % processing line93
          addi r5, r14, 0
          % processing: tmpVar45 := A
          lw r1,-8(r5)
          lw r2,-12(r5)
          sw -48(r5), r1
          sw -52(r5), r2
          % processing: tmpVar44 := tmpVar45
          lw r1,-48(r14)
          lw r2,-52(r14)
          sw -40(r14), r1
          sw -44(r14), r2
          % processing: tmpVar43 := tmpVar44
          lw r1,-40(r14)
          lw r2,-44(r14)
          sw -32(r14), r1
          sw -36(r14), r2
          % processing: tmpVar42 := tmpVar43
          lw r1,-32(r14)
          lw r2,-36(r14)
          sw -24(r14), r1
          sw -28(r14), r2
          % processing assignment statement
          addi r5, r14, 0
          lw r6,-24(r5)
          lw r7,-28(r5)
          sw -4(r5), r6
          lw r1,-4(r5)
          addi r5, r1, 0 
          addi r1, r0, 0 
          addi r2, r0, 8 
          muli r1, r1, -1
          add r1, r5, r1
          addi r1, r1, 0
          sw  0(r1), r6
          sw -4(r1), r7
          % processing line94
          % processing line94
          % processing line94
          % processing line94
          % processing line94
          addi r5, r14, 0
          % processing: tmpVar49 := B
          lw r1,-16(r5)
          lw r2,-20(r5)
          sw -80(r5), r1
          sw -84(r5), r2
          % processing: tmpVar48 := tmpVar49
          lw r1,-80(r14)
          lw r2,-84(r14)
          sw -72(r14), r1
          sw -76(r14), r2
          % processing: tmpVar47 := tmpVar48
          lw r1,-72(r14)
          lw r2,-76(r14)
          sw -64(r14), r1
          sw -68(r14), r2
          % processing: tmpVar46 := tmpVar47
          lw r1,-64(r14)
          lw r2,-68(r14)
          sw -56(r14), r1
          sw -60(r14), r2
          % processing assignment statement
          addi r5, r14, 0
          lw r6,-56(r5)
          lw r7,-60(r5)
          sw -4(r5), r6
          lw r1,-4(r5)
          addi r5, r1, 0 
          addi r1, r0, 0 
          addi r2, r0, 8 
          muli r1, r1, -1
          add r1, r5, r1
          addi r1, r1, -8
          sw  0(r1), r6
          sw -4(r1), r7
jr r15
evaluate4
          % processing line100
          % processing line100
          % processing line100
          % processing line100
          % processing line100
          % processing: tmpVar53 := 0.0
          addi r1,r0,0
          sw -48(r14),r1
          % processing: tmpVar53 := 0.0
          addi r1,r0,0
          sw -52(r14),r1
          % processing: tmpVar52 := tmpVar53
          lw r1,-48(r14)
          lw r2,-52(r14)
          sw -40(r14), r1
          sw -44(r14), r2
          % processing: tmpVar51 := tmpVar52
          lw r1,-40(r14)
          lw r2,-44(r14)
          sw -32(r14), r1
          sw -36(r14), r2
          % processing: tmpVar50 := tmpVar51
          lw r1,-32(r14)
          lw r2,-36(r14)
          sw -24(r14), r1
          sw -28(r14), r2
          % processing assignment statement
          addi r5, r14, 0
          lw r6,-24(r5)
          lw r7,-28(r5)
          sw -16(r5), r6
          sw -20(r5), r7
          % processing line101
          % processing line101
          % processing line101
          % processing line101
          % processing line101
          addi r5, r14, 0
          % processing: tmpVar57 := a
          lw r1,-4(r5)
          addi r5, r1, 0 
          addi r5, r14, 0
          % processing: tmpVar57 := a
          lw r1,0(r5)
          lw r2,-4(r5)
          sw -80(r5), r1
          sw -84(r5), r2
          % processing line101
          addi r5, r14, 0
          % processing: tmpVar59 := x
          lw r1,-8(r5)
          lw r2,-12(r5)
          sw -96(r5), r1
          sw -100(r5), r2
          % processing: tmpVar56 := tmpVar57
          lw r1,-80(r14)
          lw r2,-84(r14)
          sw -72(r14), r1
          sw -76(r14), r2
          lw r1,-72(r14)
          lw r2,-76(r14)
          lw r3,-96(r14)
          lw r4,-100(r14)
          sw -164(r14), r1
          sw -168(r14), r2
          sw -172(r14), r3
          sw -176(r14), r4
          addi r14, r14, -160
          jl r15, floatMul
          lw r1, -4(r14)
          lw r2, -8(r14)
          subi r14, r14, -160
          sw -72(r14), r1
          sw -76(r14), r2
          % processing line101
          % processing line101
          addi r5, r14, 0
          % processing: tmpVar62 := b
          lw r1,-4(r5)
          addi r5, r1, 0 
          addi r5, r14, 0
          % processing: tmpVar62 := b
          lw r1,-8(r5)
          lw r2,-12(r5)
          sw -120(r5), r1
          sw -124(r5), r2
          % processing: tmpVar61 := tmpVar62
          lw r1,-120(r14)
          lw r2,-124(r14)
          sw -112(r14), r1
          sw -116(r14), r2
          % processing: tmpVar55 := tmpVar56
          lw r1,-72(r14)
          lw r2,-76(r14)
          sw -64(r14), r1
          sw -68(r14), r2
          lw r1,-64(r14)
          lw r2,-68(r14)
          lw r3,-112(r14)
          lw r4,-116(r14)
          sw -164(r14), r1
          sw -168(r14), r2
          sw -172(r14), r3
          sw -176(r14), r4
          addi r14, r14, -160
          jl r15, floatAdd
          lw r1, -4(r14)
          lw r2, -8(r14)
          subi r14, r14, -160
          sw -64(r14), r1
          sw -68(r14), r2
          % processing: tmpVar54 := tmpVar55
          lw r1,-64(r14)
          lw r2,-68(r14)
          sw -56(r14), r1
          sw -60(r14), r2
          % processing assignment statement
          addi r5, r14, 0
          lw r6,-56(r5)
          lw r7,-60(r5)
          sw -16(r5), r6
          sw -20(r5), r7
          % processing line102
          lw r1, -128(r14)
          lw r2, -132(r14)
          sw -4(r14), r1
          sw -8(r14), r2
          % processing line102
          % processing write statement
          % processing line102
          % processing line102
          % processing line102
          % processing line102
          addi r5, r14, 0
          % processing: tmpVar66 := result
          lw r1,-16(r5)
          lw r2,-20(r5)
          sw -152(r5), r1
          sw -156(r5), r2
          % processing: tmpVar65 := tmpVar66
          lw r1,-152(r14)
          lw r2,-156(r14)
          sw -144(r14), r1
          sw -148(r14), r2
          % processing: tmpVar64 := tmpVar65
          lw r1,-144(r14)
          lw r2,-148(r14)
          sw -136(r14), r1
          sw -140(r14), r2
          % processing: tmpVar63 := tmpVar64
          lw r1,-136(r14)
          lw r2,-140(r14)
          sw -128(r14), r1
          sw -132(r14), r2
          lw r1, -128(r14)
          addi r14, r14, -160
          sw -8(r14), r1
          addi r1, r0, buf
          sw -12(r14), r1
          jl r15, intstr
          sw -8(r14),r13
          jl r15, putstr
          subi r14, r14, -160
          addi r1, r0, charE
          addi r14, r14, -160
          sw -8(r14), r1
          jl r15, putstr
          subi r14, r14, -160
          lw r1, -132(r14)
          addi r14, r14, -160
          sw -8(r14), r1
          addi r1, r0, buf
          sw -12(r14), r1
          jl r15, intstr
          sw -8(r14),r13
          jl r15, putstr
          subi r14, r14, -160
          addi r1, r0, charLineSep
          addi r14, r14, -160
          sw -8(r14), r1
          jl r15, putstr
          subi r14, r14, -160
jr r15
entry
          addi r14, r0, topaddr
          % processing pointer
          addi r1, r0, -484
          add r1, r1, r14
          sw -4(r14), r1
          % processing pointer
          addi r1, r0, -500
          add r1, r1, r14
          sw -8(r14), r1
          % processing line108
          % processing line108
          % processing line108
          % processing line108
          % processing: tmpVar70 := 2
          addi r1,r0,2
          sw -32(r14),r1
          % processing: tmpVar69 := tmpVar70
          lw r1,-32(r14)
          sw -28(r14), r1
          % processing: tmpVar68 := tmpVar69
          lw r1,-28(r14)
          sw -24(r14), r1
          % processing: tmpVar67 := tmpVar68
          lw r1,-24(r14)
          sw -20(r14), r1
          % processing line108
          % processing line108
          % processing line108
          % processing line108
          % processing: tmpVar74 := 3.5
          addi r1,r0,35
          sw -60(r14),r1
          % processing: tmpVar74 := 3.5
          addi r1,r0,1
          sw -64(r14),r1
          % processing: tmpVar73 := tmpVar74
          lw r1,-60(r14)
          lw r2,-64(r14)
          sw -52(r14), r1
          sw -56(r14), r2
          % processing: tmpVar72 := tmpVar73
          lw r1,-52(r14)
          lw r2,-56(r14)
          sw -44(r14), r1
          sw -48(r14), r2
          % processing: tmpVar71 := tmpVar72
          lw r1,-44(r14)
          lw r2,-48(r14)
          sw -36(r14), r1
          sw -40(r14), r2
          % processing line109
          % processing line109
          % processing line109
          % processing line109
          % processing: tmpVar79 := 2.0
          addi r1,r0,2
          sw -100(r14),r1
          % processing: tmpVar79 := 2.0
          addi r1,r0,0
          sw -104(r14),r1
          % processing: tmpVar77 := tmpVar78
          lw r1,-92(r14)
          lw r2,-96(r14)
          sw -84(r14), r1
          sw -88(r14), r2
          % processing: tmpVar76 := tmpVar77
          lw r1,-84(r14)
          lw r2,-88(r14)
          sw -76(r14), r1
          sw -80(r14), r2
          % processing: tmpVar75 := tmpVar76
          lw r1,-76(r14)
          lw r2,-80(r14)
          sw -68(r14), r1
          sw -72(r14), r2
          % processing line109
          % processing line109
          % processing line109
          % processing line109
          % processing: tmpVar83 := 1.0
          addi r1,r0,1
          sw -132(r14),r1
          % processing: tmpVar83 := 1.0
          addi r1,r0,0
          sw -136(r14),r1
          % processing: tmpVar82 := tmpVar83
          lw r1,-132(r14)
          lw r2,-136(r14)
          sw -124(r14), r1
          sw -128(r14), r2
          % processing: tmpVar81 := tmpVar82
          lw r1,-124(r14)
          lw r2,-128(r14)
          sw -116(r14), r1
          sw -120(r14), r2
          % processing: tmpVar80 := tmpVar81
          lw r1,-116(r14)
          lw r2,-120(r14)
          sw -108(r14), r1
          sw -112(r14), r2
          % processing line109
          % processing line109
          % processing line109
          % processing line109
          % processing: tmpVar87 := 0.0
          addi r1,r0,0
          sw -164(r14),r1
          % processing: tmpVar87 := 0.0
          addi r1,r0,0
          sw -168(r14),r1
          % processing: tmpVar86 := tmpVar87
          lw r1,-164(r14)
          lw r2,-168(r14)
          sw -156(r14), r1
          sw -160(r14), r2
          % processing: tmpVar85 := tmpVar86
          lw r1,-156(r14)
          lw r2,-160(r14)
          sw -148(r14), r1
          sw -152(r14), r2
          % processing: tmpVar84 := tmpVar85
          lw r1,-148(r14)
          lw r2,-152(r14)
          sw -140(r14), r1
          sw -144(r14), r2
          % processing line112
          % processing line112
          % processing line112
          % processing line112
          % processing line112
          % processing: tmpVar91 := 1.0
          addi r1,r0,1
          sw -196(r14),r1
          % processing: tmpVar91 := 1.0
          addi r1,r0,0
          sw -200(r14),r1
          % processing: tmpVar90 := tmpVar91
          lw r1,-196(r14)
          lw r2,-200(r14)
          sw -188(r14), r1
          sw -192(r14), r2
          % processing: tmpVar89 := tmpVar90
          lw r1,-188(r14)
          lw r2,-192(r14)
          sw -180(r14), r1
          sw -184(r14), r2
          % processing: tmpVar88 := tmpVar89
          lw r1,-180(r14)
          lw r2,-184(r14)
          sw -172(r14), r1
          sw -176(r14), r2
          % processing assignment statement
          addi r5, r14, 0
          lw r6,-172(r5)
          lw r7,-176(r5)
          sw -12(r5), r6
          sw -16(r5), r7
          % processing line113
gowhile1  
          % processing line113
          % processing line113
          % processing line113
          % processing line113
          addi r6, r14, 0
          % processing: tmpVar95 := counter
          lw r2,-12(r6)
          lw r3,-16(r6)
          sw -228(r6), r2
          sw -232(r6), r3
          % processing: tmpVar94 := tmpVar95
          lw r2,-228(r14)
          lw r3,-232(r14)
          sw -220(r14), r2
          sw -224(r14), r3
          % processing: tmpVar93 := tmpVar94
          lw r2,-220(r14)
          lw r3,-224(r14)
          sw -212(r14), r2
          sw -216(r14), r3
          % processing line113
          % processing line113
          % processing line113
          % processing: tmpVar99 := 10.0
          addi r2,r0,10
          sw -260(r14),r2
          % processing: tmpVar99 := 10.0
          addi r2,r0,0
          sw -264(r14),r2
          % processing: tmpVar98 := tmpVar99
          lw r2,-260(r14)
          lw r3,-264(r14)
          sw -252(r14), r2
          sw -256(r14), r3
          % processing: tmpVar97 := tmpVar98
          lw r2,-252(r14)
          lw r3,-256(r14)
          sw -244(r14), r2
          sw -248(r14), r3
          % processing: tmpVar92 := tmpVar93
          lw r2,-212(r14)
          lw r3,-216(r14)
          sw -204(r14), r2
          sw -208(r14), r3
          lw r2,-204(r14)
          lw r3,-208(r14)
          lw r4,-244(r14)
          lw r5,-248(r14)
          sw -528(r14), r2
          sw -532(r14), r3
          sw -536(r14), r4
          sw -540(r14), r5
          addi r14, r14, -524
          jl r15, floatLeq
          lw r2, -4(r14)
          lw r3, -8(r14)
          subi r14, r14, -524
          sw -204(r14), r2
          sw -208(r14), r3
          lw r1, -204(r14)
          bz r1, endwhile1
          % processing line115
          % processing write statement
          % processing line115
          % processing line115
          % processing line115
          % processing line115
          addi r6, r14, 0
          % processing: tmpVar103 := counter
          lw r2,-12(r6)
          lw r3,-16(r6)
          sw -292(r6), r2
          sw -296(r6), r3
          % processing: tmpVar102 := tmpVar103
          lw r2,-292(r14)
          lw r3,-296(r14)
          sw -284(r14), r2
          sw -288(r14), r3
          % processing: tmpVar101 := tmpVar102
          lw r2,-284(r14)
          lw r3,-288(r14)
          sw -276(r14), r2
          sw -280(r14), r3
          % processing: tmpVar100 := tmpVar101
          lw r2,-276(r14)
          lw r3,-280(r14)
          sw -268(r14), r2
          sw -272(r14), r3
          lw r2, -268(r14)
          addi r14, r14, -524
          sw -8(r14), r2
          addi r2, r0, buf
          sw -12(r14), r2
          jl r15, intstr
          sw -8(r14),r13
          jl r15, putstr
          subi r14, r14, -524
          addi r2, r0, charE
          addi r14, r14, -524
          sw -8(r14), r2
          jl r15, putstr
          subi r14, r14, -524
          lw r2, -272(r14)
          addi r14, r14, -524
          sw -8(r14), r2
          addi r1, r0, buf
          sw -12(r14), r2
          jl r15, intstr
          sw -8(r14),r13
          jl r15, putstr
          subi r14, r14, -524
          addi r2, r0, charLineSep
          addi r14, r14, -524
          sw -8(r14), r2
          jl r15, putstr
          subi r14, r14, -524
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
          % processing: tmpVar111 := counter
          lw r2,-12(r6)
          lw r3,-16(r6)
          sw -356(r6), r2
          sw -360(r6), r3
          % processing: tmpVar110 := tmpVar111
          lw r2,-356(r14)
          lw r3,-360(r14)
          sw -348(r14), r2
          sw -352(r14), r3
          % processing: tmpVar109 := tmpVar110
          lw r2,-348(r14)
          lw r3,-352(r14)
          sw -340(r14), r2
          sw -344(r14), r3
          % processing: tmpVar108 := tmpVar109
          lw r2,-340(r14)
          lw r3,-344(r14)
          sw -332(r14), r2
          sw -336(r14), r3
          addi r6, r14, 0
          % processing: tmpVar107 := f1
          lw r2,-4(r14)
          addi r6, r2, 0 
          sw -528(r14), r6
          %processing parameter1: x
          lw r2, -332(r14)
          sw -532(r14), r2
          lw r2, -332(r14)
          sw -532(r14), r2
          addi r14, r14, -524
          jl r15, evaluate4
          lw r2, -4(r14)
          lw r3, -8(r14)
          subi r14, r14, -524
          % processing: tmpVar106 := tmpVar107
          lw r2,-324(r14)
          lw r3,-328(r14)
          sw -316(r14), r2
          sw -320(r14), r3
          % processing: tmpVar105 := tmpVar106
          lw r2,-316(r14)
          lw r3,-320(r14)
          sw -308(r14), r2
          sw -312(r14), r3
          % processing: tmpVar104 := tmpVar105
          lw r2,-308(r14)
          lw r3,-312(r14)
          sw -300(r14), r2
          sw -304(r14), r3
          lw r2, -300(r14)
          addi r14, r14, -524
          sw -8(r14), r2
          addi r2, r0, buf
          sw -12(r14), r2
          jl r15, intstr
          sw -8(r14),r13
          jl r15, putstr
          subi r14, r14, -524
          addi r2, r0, charE
          addi r14, r14, -524
          sw -8(r14), r2
          jl r15, putstr
          subi r14, r14, -524
          lw r2, -304(r14)
          addi r14, r14, -524
          sw -8(r14), r2
          addi r1, r0, buf
          sw -12(r14), r2
          jl r15, intstr
          sw -8(r14),r13
          jl r15, putstr
          subi r14, r14, -524
          addi r2, r0, charLineSep
          addi r14, r14, -524
          sw -8(r14), r2
          jl r15, putstr
          subi r14, r14, -524
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
          % processing: tmpVar119 := counter
          lw r2,-12(r6)
          lw r3,-16(r6)
          sw -420(r6), r2
          sw -424(r6), r3
          % processing: tmpVar118 := tmpVar119
          lw r2,-420(r14)
          lw r3,-424(r14)
          sw -412(r14), r2
          sw -416(r14), r3
          % processing: tmpVar117 := tmpVar118
          lw r2,-412(r14)
          lw r3,-416(r14)
          sw -404(r14), r2
          sw -408(r14), r3
          % processing: tmpVar116 := tmpVar117
          lw r2,-404(r14)
          lw r3,-408(r14)
          sw -396(r14), r2
          sw -400(r14), r3
          addi r6, r14, 0
          % processing: tmpVar115 := f2
          lw r2,-8(r14)
          addi r6, r2, 0 
          sw -528(r14), r6
          %processing parameter1: x
          lw r2, -396(r14)
          sw -532(r14), r2
          lw r2, -396(r14)
          sw -532(r14), r2
          addi r14, r14, -524
          jl r15, evaluate1
          lw r2, -4(r14)
          lw r3, -8(r14)
          subi r14, r14, -524
          % processing: tmpVar114 := tmpVar115
          lw r2,-388(r14)
          lw r3,-392(r14)
          sw -380(r14), r2
          sw -384(r14), r3
          % processing: tmpVar113 := tmpVar114
          lw r2,-380(r14)
          lw r3,-384(r14)
          sw -372(r14), r2
          sw -376(r14), r3
          % processing: tmpVar112 := tmpVar113
          lw r2,-372(r14)
          lw r3,-376(r14)
          sw -364(r14), r2
          sw -368(r14), r3
          lw r2, -364(r14)
          addi r14, r14, -524
          sw -8(r14), r2
          addi r2, r0, buf
          sw -12(r14), r2
          jl r15, intstr
          sw -8(r14),r13
          jl r15, putstr
          subi r14, r14, -524
          addi r2, r0, charE
          addi r14, r14, -524
          sw -8(r14), r2
          jl r15, putstr
          subi r14, r14, -524
          lw r2, -368(r14)
          addi r14, r14, -524
          sw -8(r14), r2
          addi r1, r0, buf
          sw -12(r14), r2
          jl r15, intstr
          sw -8(r14),r13
          jl r15, putstr
          subi r14, r14, -524
          addi r2, r0, charLineSep
          addi r14, r14, -524
          sw -8(r14), r2
          jl r15, putstr
          subi r14, r14, -524
          % processing line118
          % processing line118
          % processing line118
          % processing line118
          % processing line118
          addi r6, r14, 0
          % processing: tmpVar123 := counter
          lw r2,-12(r6)
          lw r3,-16(r6)
          sw -452(r6), r2
          sw -456(r6), r3
          % processing: tmpVar122 := tmpVar123
          lw r2,-452(r14)
          lw r3,-456(r14)
          sw -444(r14), r2
          sw -448(r14), r3
          % processing line118
          % processing line118
          % processing: tmpVar126 := 1.0
          addi r2,r0,1
          sw -476(r14),r2
          % processing: tmpVar126 := 1.0
          addi r2,r0,0
          sw -480(r14),r2
          % processing: tmpVar125 := tmpVar126
          lw r2,-476(r14)
          lw r3,-480(r14)
          sw -468(r14), r2
          sw -472(r14), r3
          % processing: tmpVar121 := tmpVar122
          lw r2,-444(r14)
          lw r3,-448(r14)
          sw -436(r14), r2
          sw -440(r14), r3
          lw r2,-436(r14)
          lw r3,-440(r14)
          lw r4,-468(r14)
          lw r5,-472(r14)
          sw -528(r14), r2
          sw -532(r14), r3
          sw -536(r14), r4
          sw -540(r14), r5
          addi r14, r14, -524
          jl r15, floatAdd
          lw r2, -4(r14)
          lw r3, -8(r14)
          subi r14, r14, -524
          sw -436(r14), r2
          sw -440(r14), r3
          % processing: tmpVar120 := tmpVar121
          lw r2,-436(r14)
          lw r3,-440(r14)
          sw -428(r14), r2
          sw -432(r14), r3
          % processing assignment statement
          addi r6, r14, 0
          lw r7,-428(r6)
          lw r8,-432(r6)
          sw -12(r6), r7
          sw -16(r6), r8
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
